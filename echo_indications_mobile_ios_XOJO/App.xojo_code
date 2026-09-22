#tag Class
Protected Class App
Inherits MobileApplication
	#tag CompatibilityFlags = TargetIOS
	#tag Event
		Sub Opening()
		  // Singletons that every screen reaches via App.API / App.Auth.
		  Self.Auth = New AuthManager
		  Self.API = New APIClient
		  Self.Auth.LoadFromDisk
		End Sub
	#tag EndEvent


	#tag Property, Flags = &h0
		API As APIClient
	#tag EndProperty

	#tag Property, Flags = &h0
		Auth As AuthManager
	#tag EndProperty


	#tag Constant, Name = NHSBlue, Type = Color, Dynamic = False, Default = \"&c006AB5", Scope = Public
	#tag EndConstant


End Class
#tag EndClass
