#tag Class
Protected Class AuthManager
	#tag Method, Flags = &h0
		Function IsAuthenticated() As Boolean
		  Return Self.Token <> ""
		End Function
	#tag EndMethod

	#tag Method, Flags = &h0
		Sub LoadFromDisk()
		  // Read previously-persisted login from auth.json in Documents.
		  // Leaves properties empty if file missing or invalid (i.e. first launch).
		  Var f As FolderItem = AuthFile
		  If f Is Nil Or Not f.Exists Then Return

		  Try
		    Var t As TextInputStream = TextInputStream.Open(f)
		    Var content As String = t.ReadAll
		    t.Close

		    Var j As New JSONItem(content)
		    Self.Token = j.Lookup("token", "").StringValue
		    Self.UserID = j.Lookup("user_id", 0).IntegerValue
		    Self.Username = j.Lookup("username", "").StringValue
		  Catch err As RuntimeException
		    System.DebugLog("AuthManager.LoadFromDisk: " + err.Message)
		    Self.Token = ""
		    Self.UserID = 0
		    Self.Username = ""
		  End Try
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h0
		Sub Persist()
		  // Write current Token/UserID/Username to auth.json.
		  Var f As FolderItem = AuthFile
		  If f Is Nil Then Return

		  Var j As New JSONItem
		  j.Value("token") = Self.Token
		  j.Value("user_id") = Self.UserID
		  j.Value("username") = Self.Username

		  Try
		    If f.Exists Then f.Remove
		    Var t As TextOutputStream = TextOutputStream.Create(f)
		    t.Write(j.ToString)
		    t.Close
		  Catch err As RuntimeException
		    System.DebugLog("AuthManager.Persist: " + err.Message)
		  End Try
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h0
		Sub Clear()
		  // Wipe in-memory and on-disk credentials. Called on logout / 401.
		  Self.Token = ""
		  Self.UserID = 0
		  Self.Username = ""

		  Var f As FolderItem = AuthFile
		  If f <> Nil And f.Exists Then
		    Try
		      f.Remove
		    Catch
		    End Try
		  End If
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Function AuthFile() As FolderItem
		  // iOS apps get a sandboxed Documents folder via SpecialFolder.Documents.
		  Var docs As FolderItem = SpecialFolder.Documents
		  If docs Is Nil Then Return Nil
		  Return docs.Child("auth.json")
		End Function
	#tag EndMethod


	#tag Property, Flags = &h0
		Token As String
	#tag EndProperty

	#tag Property, Flags = &h0
		UserID As Integer
	#tag EndProperty

	#tag Property, Flags = &h0
		Username As String
	#tag EndProperty


End Class
#tag EndClass
