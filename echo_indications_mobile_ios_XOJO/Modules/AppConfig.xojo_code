#tag Module
Protected Module AppConfig
	#tag Method, Flags = &h0
		Function BaseURL() As String
		  // Compiler-conditional environment switching.
		  // Xojo IDE Run = DebugBuild = True  -> LAN dev server
		  // Build (Release) = DebugBuild = False -> production droplet
		  #If DebugBuild Then
		    Return "http://192.168.68.55:8080"
		  #Else
		    Return "https://api.heartimaging.org"
		  #EndIf
		End Function
	#tag EndMethod

	#tag Method, Flags = &h0
		Function URL(path As String) As String
		  // Build a full API URL. `path` should start with "/api/v1/...".
		  Var base As String = AppConfig.BaseURL
		  If base.EndsWith("/") Then base = base.Left(base.Length - 1)
		  If Not path.BeginsWith("/") Then path = "/" + path
		  Return base + path
		End Function
	#tag EndMethod


	#tag Constant, Name = API_VERSION, Type = String, Dynamic = False, Default = \"v1", Scope = Public
	#tag EndConstant


End Module
#tag EndModule
