#tag Class
Protected Class APIClient
	#tag Method, Flags = &h0
		Sub Login(username As String, password As String)
		  // POST /api/v1/login — async. Raises LoginCompleted when done.
		  // On success, persists the token via App.Auth so subsequent calls
		  // can attach Authorization: Bearer.
		  mLoginConn = New URLConnection
		  AddHandler mLoginConn.ContentReceived, AddressOf OnLoginReceived
		  AddHandler mLoginConn.Error, AddressOf OnLoginError

		  Var body As New JSONItem
		  body.Value("username") = username
		  body.Value("password") = password

		  mLoginConn.RequestHeader("Content-Type") = "application/json"
		  mLoginConn.SetRequestContent(body.ToString, "application/json")
		  mLoginConn.Send("POST", AppConfig.URL("/api/v1/login"))
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub OnLoginReceived(sender As URLConnection, URL As String, httpStatus As Integer, content As String)
		  #Pragma Unused sender
		  #Pragma Unused URL

		  Try
		    Var j As New JSONItem(content)

		    If httpStatus = 200 Then
		      App.Auth.Token = j.Lookup("token", "").StringValue
		      If j.HasName("user") Then
		        Var u As JSONItem = j.Value("user")
		        App.Auth.UserID = u.Lookup("id", 0).IntegerValue
		        App.Auth.Username = u.Lookup("username", "").StringValue
		      End If
		      App.Auth.Persist
		      RaiseEvent LoginCompleted(True, "")
		    Else
		      Var msg As String = j.Lookup("error", "Login failed (HTTP " + httpStatus.ToString + ")").StringValue
		      RaiseEvent LoginCompleted(False, msg)
		    End If
		  Catch err As RuntimeException
		    RaiseEvent LoginCompleted(False, "Invalid response from server")
		  End Try
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub OnLoginError(sender As URLConnection, err As RuntimeException)
		  #Pragma Unused sender
		  RaiseEvent LoginCompleted(False, "Network error: " + err.Message)
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h0
		Sub GetIndications()
		  // GET /api/v1/indications — public endpoint, but we attach the Bearer
		  // token if we have one (forward-compatible with admin-only future fields).
		  mListConn = New URLConnection
		  AddHandler mListConn.ContentReceived, AddressOf OnIndicationsReceived
		  AddHandler mListConn.Error, AddressOf OnIndicationsError

		  If App.Auth <> Nil And App.Auth.IsAuthenticated Then
		    mListConn.RequestHeader("Authorization") = "Bearer " + App.Auth.Token
		  End If
		  mListConn.Send("GET", AppConfig.URL("/api/v1/indications"))
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub OnIndicationsReceived(sender As URLConnection, URL As String, httpStatus As Integer, content As String)
		  #Pragma Unused sender
		  #Pragma Unused URL

		  Var results() As Indication

		  Try
		    If httpStatus = 200 Then
		      Var arr As New JSONItem(content)
		      For i As Integer = 0 To arr.Count - 1
		        results.Add Indication.FromJSON(arr.Child(i))
		      Next
		      RaiseEvent IndicationsLoaded(True, results, "")
		    Else
		      Var j As New JSONItem(content)
		      Var msg As String = j.Lookup("error", "Failed to load indications (HTTP " + httpStatus.ToString + ")").StringValue
		      RaiseEvent IndicationsLoaded(False, results, msg)
		    End If
		  Catch err As RuntimeException
		    RaiseEvent IndicationsLoaded(False, results, "Invalid response from server")
		  End Try
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub OnIndicationsError(sender As URLConnection, err As RuntimeException)
		  #Pragma Unused sender
		  Var empty() As Indication
		  RaiseEvent IndicationsLoaded(False, empty, "Network error: " + err.Message)
		End Sub
	#tag EndMethod


	#tag Hook, Flags = &h0
		Event LoginCompleted(success As Boolean, errorMessage As String)
	#tag EndHook

	#tag Hook, Flags = &h0
		Event IndicationsLoaded(success As Boolean, indications() As Indication, errorMessage As String)
	#tag EndHook


	#tag Property, Flags = &h21
		Private mLoginConn As URLConnection
	#tag EndProperty

	#tag Property, Flags = &h21
		Private mListConn As URLConnection
	#tag EndProperty


	#tag ViewBehavior
		#tag ViewProperty
			Name="Name"
			Visible=true
			Group="ID"
			InitialValue=""
			Type="String"
			EditorType=""
		#tag EndViewProperty
		#tag ViewProperty
			Name="Index"
			Visible=true
			Group="ID"
			InitialValue="-2147483648"
			Type="Integer"
			EditorType=""
		#tag EndViewProperty
		#tag ViewProperty
			Name="Super"
			Visible=true
			Group="ID"
			InitialValue=""
			Type="String"
			EditorType=""
		#tag EndViewProperty
		#tag ViewProperty
			Name="Left"
			Visible=true
			Group="Position"
			InitialValue="0"
			Type="Integer"
			EditorType=""
		#tag EndViewProperty
		#tag ViewProperty
			Name="Top"
			Visible=true
			Group="Position"
			InitialValue="0"
			Type="Integer"
			EditorType=""
		#tag EndViewProperty
	#tag EndViewBehavior
End Class
#tag EndClass
