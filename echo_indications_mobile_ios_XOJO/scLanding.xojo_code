#tag MobileScreen
Begin MobileScreen scLanding
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
   Title           =   "Home"
   Top             =   0
   _mTabBarVisible =   False
   Begin MobileButton btnSearch
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnSearch, 1, <Parent>, 1, False, +1.00, 4, 1, *kStdGapCtlToViewH, , True
      AutoLayout      =   btnSearch, 2, btnAbout, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   btnSearch, 3, <Parent>, 3, False, +1.00, 4, 1, 235, , True
      AutoLayout      =   btnSearch, 4, <Parent>, 4, False, +1.00, 4, 1, -531, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Search indications"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   235
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnBrowse
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnBrowse, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   btnBrowse, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   btnBrowse, 3, <Parent>, 3, False, +1.00, 4, 1, 289, , True
      AutoLayout      =   btnBrowse, 4, <Parent>, 4, False, +1.00, 4, 1, -477, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Browse indications (by context)"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   289
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnCDS
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnCDS, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   btnCDS, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   btnCDS, 3, <Parent>, 3, False, +1.00, 4, 1, 343, , True
      AutoLayout      =   btnCDS, 3, <Parent>, 3, False, +1.00, 4, 1, 343, , True
      AutoLayout      =   btnCDS, 4, <Parent>, 4, False, +1.00, 4, 1, -423, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Clinical Decision Support Tool"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   343
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnAdmin
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnAdmin, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   btnAdmin, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   btnAdmin, 3, <Parent>, 3, False, +1.00, 4, 1, 746, , True
      AutoLayout      =   btnAdmin, 4, <Parent>, 4, False, +1.00, 4, 1, -20, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Admin login"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   746
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnFeedback
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnFeedback, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   btnFeedback, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   btnFeedback, 3, <Parent>, 3, False, +1.00, 4, 1, 493, , True
      AutoLayout      =   btnFeedback, 4, <Parent>, 4, False, +1.00, 4, 1, -273, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "Submit issue / feedback "
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   493
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileButton btnAbout
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      AutoLayout      =   btnAbout, 1, btnFeedback, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   btnAbout, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   btnAbout, 3, btnFeedback, 4, False, +1.00, 4, 1, *kStdControlGapV, , True
      AutoLayout      =   btnAbout, 8, , 0, False, +1.00, 4, 1, 46, , True
      BackgroundColor =   
      BorderColor     =   
      BorderWidth     =   0
      Caption         =   "About"
      CaptionColor    =   &c007AFF00
      ControlCount    =   0
      CornerSize      =   0
      Enabled         =   True
      Height          =   46
      Icon            =   0
      Left            =   20
      LockedInPosition=   False
      Scope           =   0
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   547
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
      AutoLayout      =   lblAppName, 7, , 0, False, +1.00, 4, 1, 375, , True
      AutoLayout      =   lblAppName, 3, <Parent>, 3, False, +1.00, 4, 1, 100, , True
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
      Top             =   100
      Visible         =   True
      Width           =   375
      _ClosingFired   =   False
   End
   Begin MobileLabel lblTagLine
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   1
      AutoLayout      =   lblTagLine, 1, <Parent>, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblTagLine, 7, , 0, False, +1.00, 4, 1, 375, , True
      AutoLayout      =   lblTagLine, 3, <Parent>, 3, False, +1.00, 4, 1, 147, , True
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
      Top             =   147
      Visible         =   True
      Width           =   375
      _ClosingFired   =   False
   End
End
#tag EndMobileScreen

#tag WindowCode
#tag EndWindowCode

#tag Events btnSearch
	#tag Event
		Sub Pressed()
		  Var list As New scIndicationsList
		  list.SetMode(scIndicationsList.EntryModes.Search)
		  Self.PushTo(list)
		End Sub
	#tag EndEvent
#tag EndEvents

#tag Events btnBrowse
	#tag Event
		Sub Pressed()
		  Var list As New scIndicationsList
		  list.SetMode(scIndicationsList.EntryModes.Browse)
		  Self.PushTo(list)
		End Sub
	#tag EndEvent
#tag EndEvents

#tag Events btnCDS
	#tag Event
		Sub Pressed()
		  Self.PushTo(New scCDS)
		End Sub
	#tag EndEvent
#tag EndEvents

#tag Events btnFeedback
	#tag Event
		Sub Pressed()
		  // Placeholder until scFeedback screen exists. The /api/v1/issues
		  // endpoint already accepts anonymous submissions.
		  MessageBox("Feedback form coming soon — for now please use the web app.")
		End Sub
	#tag EndEvent
#tag EndEvents

#tag Events btnAdmin
	#tag Event
		Sub Pressed()
		  Self.PushTo(New scLogin)
		End Sub
	#tag EndEvent
#tag EndEvents

#tag Events btnAbout
	#tag Event
		Sub Pressed()
		  MessageBox("ECHO Indications" + EndOfLine + "AUC reference for echocardiography" + EndOfLine + EndOfLine + "heartimaging.org")
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
