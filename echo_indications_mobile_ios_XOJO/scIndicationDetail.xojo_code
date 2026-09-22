#tag MobileScreen
Begin MobileScreen scIndicationDetail
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
   TintColor       =   &c00000000
   Title           =   "Indication Detail"
   Top             =   0
   _mTabBarVisible =   False
   Begin MobileLabel lblTitle
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblTitle, 1, <Parent>, 1, False, +1.00, 4, 1, *kStdGapCtlToViewH, , True
      AutoLayout      =   lblTitle, 2, <Parent>, 2, False, +1.00, 4, 1, -*kStdGapCtlToViewH, , True
      AutoLayout      =   lblTitle, 3, <Parent>, 3, False, +1.00, 4, 1, 80, , True
      AutoLayout      =   lblTitle, 8, , 0, False, +1.00, 4, 1, 88, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   88
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Indication Title"
      TextColor       =   &c000000
      TextFont        =   "System Bold		"
      TextSize        =   22
      TintColor       =   
      Top             =   80
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblKeywords
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblKeywords, 1, lblTitle, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblKeywords, 2, lblTitle, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblKeywords, 3, <Parent>, 3, False, +1.00, 4, 1, 185, , True
      AutoLayout      =   lblKeywords, 8, , 0, False, +1.00, 4, 1, 30, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   30
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Keywords"
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   185
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblComments
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblComments, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblComments, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblComments, 3, <Parent>, 3, False, +1.00, 4, 1, 223, , True
      AutoLayout      =   lblComments, 8, , 0, False, +1.00, 4, 1, 90, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   90
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Comments"
      TextColor       =   &c000000
      TextFont        =   "System		"
      TextSize        =   0
      TintColor       =   
      Top             =   223
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblPrimaryCare
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblPrimaryCare, 1, lblComments, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblPrimaryCare, 2, lblComments, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lblPrimaryCare, 3, <Parent>, 3, False, +1.00, 4, 1, 339, , True
      AutoLayout      =   lblPrimaryCare, 8, , 0, False, +1.00, 4, 1, 30, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   30
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "AUC primary care"
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   339
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblSecondaryInpatient
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblSecondaryInpatient, 3, <Parent>, 3, False, +1.00, 4, 1, 377, , True
      AutoLayout      =   lblSecondaryInpatient, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblSecondaryInpatient, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblSecondaryInpatient, 8, , 0, False, +1.00, 4, 1, 30, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   30
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Secondary care IP"
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   377
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblSecondaryOutpatient
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblSecondaryOutpatient, 3, <Parent>, 3, False, +1.00, 4, 1, 415, , True
      AutoLayout      =   lblSecondaryOutpatient, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblSecondaryOutpatient, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblSecondaryOutpatient, 8, , 0, False, +1.00, 4, 1, 30, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   30
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Secondary care OP"
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   415
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblUrgency
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblUrgency, 3, <Parent>, 3, False, +1.00, 4, 1, 453, , True
      AutoLayout      =   lblUrgency, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblUrgency, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblUrgency, 8, , 0, False, +1.00, 4, 1, 30, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   30
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Urgency"
      TextColor       =   &c000000
      TextFont        =   ""
      TextSize        =   0
      TintColor       =   
      Top             =   453
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblContexts
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblContexts, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblContexts, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblContexts, 3, <Parent>, 3, False, +1.00, 4, 1, 511, , True
      AutoLayout      =   lblContexts, 8, , 0, False, +1.00, 4, 1, 59, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   59
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Contexts"
      TextColor       =   &c000000
      TextFont        =   "System		"
      TextSize        =   0
      TintColor       =   
      Top             =   511
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
   Begin MobileLabel lblSources
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AdjustTextSizeToFit=   False
      Alignment       =   0
      AutoLayout      =   lblSources, 3, <Parent>, 3, False, +1.00, 4, 1, 578, , True
      AutoLayout      =   lblSources, 1, <Parent>, 1, False, +1.00, 4, 1, 20, , True
      AutoLayout      =   lblSources, 7, , 0, False, +1.00, 4, 1, 335, , True
      AutoLayout      =   lblSources, 8, , 0, False, +1.00, 4, 1, 59, , True
      ControlCount    =   0
      Enabled         =   True
      Height          =   59
      Left            =   20
      LineBreakMode   =   0
      LockedInPosition=   False
      MaximumCharactersAllowed=   0
      Scope           =   0
      SelectedText    =   ""
      SelectionLength =   0
      SelectionStart  =   0
      Text            =   "Sources"
      TextColor       =   &c000000
      TextFont        =   "System		"
      TextSize        =   0
      TintColor       =   
      Top             =   578
      Visible         =   True
      Width           =   335
      _ClosingFired   =   False
   End
End
#tag EndMobileScreen

#tag WindowCode
	#tag Event
		Sub Opening()
		  If mInd Is Nil Then Return
		  
		  Self.Title = mInd.Title
		  
		  Self.lblTitle.Text = mInd.Title
		  Self.lblKeywords.Text = If(mInd.Keywords = "", "—", mInd.Keywords)
		  Self.lblComments.Text = If(mInd.Comments = "", "—", mInd.Comments)
		  Self.lblPrimaryCare.Text = If(mInd.PrimaryCare = "", "—", mInd.PrimaryCare)
		  Self.lblSecondaryInpatient.Text = If(mInd.SecondaryInpatient = "", "—", mInd.SecondaryInpatient)
		  Self.lblSecondaryOutpatient.Text = If(mInd.SecondaryOutpatient = "", "—", mInd.SecondaryOutpatient)
		  Self.lblUrgency.Text = If(mInd.Urgency = "", "—", mInd.Urgency)
		  Self.lblSources.Text = If(mInd.SourceList = "", "—", mInd.SourceList)
		  Self.lblContexts.Text = If(mInd.ContextNames.LastIndex < 0, "—", String.FromArray(mInd.ContextNames, ", "))
		End Sub
	#tag EndEvent


	#tag Method, Flags = &h0
		Sub SetIndication(ind As Indication)
		  mInd = ind
		End Sub
	#tag EndMethod


	#tag Property, Flags = &h21
		Private mInd As Indication
	#tag EndProperty


#tag EndWindowCode

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
