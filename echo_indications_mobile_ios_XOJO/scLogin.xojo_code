#tag MobileScreen
Begin MobileScreen scLogin
   BackButtonCaption=   ""
   BackgroundColor =   
   Compatibility   =   ""
   ControlCount    =   0
   Device = 7
   HasNavigationBar=   True
   LargeTitleDisplayMode=   2
   Left            =   0
   NavigationBarColor=   
   NavigationBarTextColor=   
   Orientation = 0
   ScaleFactor     =   0.0
   TabBarVisible   =   True
   TabIcon         =   0
   TintColor       =   
   Title           =   "Sign in"
   Top             =   0
   _mTabBarVisible =   False
   Begin MobileTextField txtUsername
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AllowAutoCorrection=   False
      AllowSpellChecking=   False
      AutoCapitalizationType=   0
      AutoLayout      =   txtUsername, 1, <Parent>, 1, False, +1.00, 4, 1, *kStdGapCtlToViewH, , True
      AutoLayout      =   txtUsername, 2, <Parent>, 2, False, +1.00, 4, 1, -*kStdGapCtlToViewH, , True
      AutoLayout      =   txtUsername, 3, <Parent>, 3, False, +1.00, 4, 1, 275, , True
      AutoLayout      =   txtUsername, 8, , 0, False, +1.00, 4, 1, 34, , True
      BorderStyle     =   3
      ControlCount    =   0
      Enabled         =   True
      Height          =   34
      Hint            =   "Username"
      HintColor       =   
      InputType       =   0
      Left            =   20
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Password        =   False
      ReadOnly        =   False
      ReturnCaption   =   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   ""
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   275
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileTextField txtPassword
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AllowAutoCorrection=   False
      AllowSpellChecking=   False
      AutoCapitalizationType=   0
      AutoLayout      =   txtPassword, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   txtPassword, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   txtPassword, 3, <Parent>, 3, False, +1.00, 4, 1, 362, , True
      AutoLayout      =   txtPassword, 8, , 0, False, +1.00, 4, 1, 34, , True
      BorderStyle     =   3
      ControlCount    =   0
      Enabled         =   True
      Height          =   34
      Hint            =   "Password"
      HintColor       =   
      InputType       =   0
      Left            =   20
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Password        =   True
      ReadOnly        =   False
      ReturnCaption   =   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   ""
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   362
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblError
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   1
      AutoLayout      =   lblError, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblError, 2, btnLogin, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblError, 3, <Parent>, 3, False, +1.00, 4, 1, 456, , True
      AutoLayout      =   lblError, 8, , 0, False, +1.00, 4, 1, 83, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   83
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   ""
      TextColor       =   &cFF260000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   456
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnLogin
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnLogin, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   btnLogin, 2, <Parent>, 2, False, +1.00, 4, 1, -20, , True
      AutoLayout      =   btnLogin, 4, <Parent>, 4, False, +1.00, 4, 1, -*kStdGapCtlToViewV, , True
      AutoLayout      =   btnLogin, 8, , 0, False, +1.00, 4, 1, 45, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Log in"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   45
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   747
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblAppName
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   1
      AutoLayout      =   lblAppName, 1, <Parent>, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblAppName, 2, <Parent>, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblAppName, 3, <Parent>, 3, False, +1.00, 4, 1, 66, , True
      AutoLayout      =   lblAppName, 8, , 0, False, +1.00, 4, 1, 57, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   57
      Left            =   0
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "ECHOindications.org"
      TextColor       =   &c006AB500
      TextFont        =   "System Bold		"
      TextSize        =   32
      TintColor       =   
      Top             =   66
      Visible         =   True
      Width           =   375
      _ClosingFired   =   False
   End
   Begin MobileLabel lblTagLine
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   1
      AutoLayout      =   lblTagLine, 1, lblAppName, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblTagLine, 7, , 0, False, +1.00, 4, 1, 375, , True
      AutoLayout      =   lblTagLine, 3, <Parent>, 3, False, +1.00, 4, 1, 113, , True
      AutoLayout      =   lblTagLine, 8, , 0, False, +1.00, 4, 1, 33, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   33
      Left            =   0
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Appropriate Use Criteria for Echocardiography"
      TextColor       =   &c006AB500
      TextFont        =   "System		"
      TextSize        =   14
      TintColor       =   
      Top             =   113
      Visible         =   True
      Width           =   375
      _ClosingFired   =   False
   End
   Begin MobileLabel lblTagLine1
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblTagLine1, 1, btnLogin, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblTagLine1, 2, btnLogin, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblTagLine1, 3, <Parent>, 3, False, +1.00, 4, 1, 234, , True
      AutoLayout      =   lblTagLine1, 8, , 0, False, +1.00, 4, 1, 33, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   33
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Log in as admin"
      TextColor       =   &c00000000
      TextFont        =   "System		"
      TextSize        =   14
      TintColor       =   
      Top             =   234
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
End
#tag EndMobileScreen

#tag WindowCode
	#tag Event
		Sub Closing()
		  // Detach the handler so a stale instance can't fire on a later login.
		  If App.API <> Nil Then
		    RemoveHandler App.API.LoginCompleted, AddressOf HandleLoginCompleted
		  End If
		End Sub
	#tag EndEvent

	#tag Event
		Sub Opening()
		  // Login is admin-only — accessed from scLanding's Admin button.
		  // If already authenticated, just close and return to landing.
		  If App.Auth <> Nil And App.Auth.IsAuthenticated Then
		    Self.Close
		    Return
		  End If

		  AddHandler App.API.LoginCompleted, AddressOf HandleLoginCompleted
		End Sub
	#tag EndEvent


	#tag Method, Flags = &h0
		Sub DoLogin(username As String, password As String)
		  // Called from btnLogin.Pressed. Disables UI controls while the request
		  // is in flight; HandleLoginCompleted re-enables them.
		  // (The control references below assume controls named in the IDE — see
		  // SETUP.md or the iOS scaffolding instructions for required control names.)
		  If username.Trim = "" Or password = "" Then
		    SetError("Enter username and password")
		    Return
		  End If
		  
		  SetError("")
		  SetBusy(True)
		  App.API.Login(username, password)
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub HandleLoginCompleted(sender As APIClient, success As Boolean, errorMessage As String)
		  #Pragma Unused sender
		  SetBusy(False)
		  
		  If success Then
		    // Pop back to scLanding. Admin features will check App.Auth.IsAuthenticated
		    // at the point of use rather than auto-navigating somewhere.
		    Self.Close
		  Else
		    SetError(If(errorMessage = "", "Login failed", errorMessage))
		  End If
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub SetBusy(busy As Boolean)
		  // Disables inputs while the network request is in flight.
		  Self.txtUsername.Enabled = Not busy
		  Self.txtPassword.Enabled = Not busy
		  Self.btnLogin.Enabled = Not busy
		  Self.btnLogin.Caption = If(busy, "Signing in…", "Sign in")
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub SetError(message As String)
		  // Updates the lblError control. Wrapped so we can swap the UI later.
		  Self.lblError.Text = message
		  Self.lblError.Visible = (message <> "")
		End Sub
	#tag EndMethod


#tag EndWindowCode

#tag Events btnLogin
	#tag Event
		Sub Pressed()
		  DoLogin(Self.txtUsername.Text, Self.txtPassword.Text)
		End Sub
	#tag EndEvent
#tag EndEvents
#tag ViewBehavior
	#tag ViewProperty
		Name="Index"
		Visible=true
		Group="ID"
		InitialValue="-2147483648"
		Type="Integer"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="Name"
		Visible=true
		Group="ID"
		InitialValue=""
		Type="String"
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
	#tag ViewProperty
		Name="BackButtonCaption"
		Visible=true
		Group="Behavior"
		InitialValue=""
		Type="String"
		EditorType="MultiLineEditor"
	#tag EndViewProperty
	#tag ViewProperty
		Name="HasNavigationBar"
		Visible=true
		Group="Behavior"
		InitialValue="True"
		Type="Boolean"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="TabIcon"
		Visible=true
		Group="Behavior"
		InitialValue=""
		Type="Picture"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="Title"
		Visible=true
		Group="Behavior"
		InitialValue="Untitled"
		Type="String"
		EditorType="MultiLineEditor"
	#tag EndViewProperty
	#tag ViewProperty
		Name="LargeTitleDisplayMode"
		Visible=true
		Group="Behavior"
		InitialValue="2"
		Type="MobileScreen.LargeTitleDisplayModes"
		EditorType="Enum"
		#tag EnumValues
			"0 - Automatic"
			"1 - Always"
			"2 - Never"
		#tag EndEnumValues
	#tag EndViewProperty
	#tag ViewProperty
		Name="TabBarVisible"
		Visible=true
		Group="Behavior"
		InitialValue="True"
		Type="Boolean"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="TintColor"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="ColorGroup"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="ControlCount"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="Integer"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="ScaleFactor"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="Double"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="_mTabBarVisible"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="Boolean"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="BackgroundColor"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="ColorGroup"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="NavigationBarColor"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="ColorGroup"
		EditorType=""
	#tag EndViewProperty
	#tag ViewProperty
		Name="NavigationBarTextColor"
		Visible=false
		Group="Behavior"
		InitialValue=""
		Type="ColorGroup"
		EditorType=""
	#tag EndViewProperty
#tag EndViewBehavior
