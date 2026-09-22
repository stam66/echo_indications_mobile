#tag MobileScreen
Begin MobileScreen scIndicationsList
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
   Title           =   "Indications"
   Top             =   0
   _mTabBarVisible =   False
   Begin iOSMobileTable lstIndications
      AccessibilityHint=   ""
      AccessibilityLabel=   ""
      AllowRefresh    =   False
      AllowSearch     =   False
      AutoLayout      =   lstIndications, 1, <Parent>, 1, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lstIndications, 2, <Parent>, 2, False, +1.00, 4, 1, 0, , True
      AutoLayout      =   lstIndications, 3, , 0, False, +1.00, 4, 1, 73, , True
      AutoLayout      =   lstIndications, 4, BottomLayoutGuide, 4, False, +1.00, 4, 1, 0, , True
      BackgroundColor =   
      ControlCount    =   0
      EditingEnabled  =   False
      Enabled         =   True
      EstimatedRowHeight=   -1
      Format          =   0
      Height          =   739
      Left            =   0
      LockedInPosition=   False
      Scope           =   0
      SectionBackgroundColor=   
      SectionCount    =   0
      SectionTextColor=   
      SelectedRowColor=   
      TintColor       =   
      Top             =   73
      Visible         =   True
      Width           =   375
      _ClosingFired   =   False
      _OpeningCompleted=   False
   End
End
#tag EndMobileScreen

#tag WindowCode
	#tag Event
		Sub Opening()
		  mAllIndications.ResizeTo(-1)
		  mIndications.ResizeTo(-1)
		  AddHandler App.API.IndicationsLoaded, AddressOf HandleIndicationsLoaded
		  App.API.GetIndications
		End Sub
	#tag EndEvent

	#tag Event
		Sub Closing()
		  If App.API <> Nil Then
		    RemoveHandler App.API.IndicationsLoaded, AddressOf HandleIndicationsLoaded
		  End If
		End Sub
	#tag EndEvent


	#tag Method, Flags = &h0
		Sub SetMode(mode As EntryModes)
		  // Called by scLanding before pushing this screen. Differentiates the
		  // initial state — Search-mode focuses the search field on appear;
		  // Browse-mode just shows the full list.
		  mEntryMode = mode
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub HandleIndicationsLoaded(sender As APIClient, success As Boolean, indications() As Indication, errorMessage As String)
		  #Pragma Unused sender

		  mAllIndications.ResizeTo(-1)

		  If Not success Then
		    mIndications.ResizeTo(-1)
		    Self.lstIndications.RemoveAllRows
		    If Self.lstIndications.SectionCount = 0 Then
		      Self.lstIndications.AddSection("")
		    End If
		    Var cell As MobileTableCellData = Self.lstIndications.CreateCell
		    cell.Text = "Could not load"
		    cell.DetailText = errorMessage
		    Self.lstIndications.AddRow(0, cell)
		    Return
		  End If

		  For Each ind As Indication In indications
		    mAllIndications.Add ind
		  Next

		  ApplyFilter("")
		End Sub
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Sub ApplyFilter(searchText As String)
		  // Rebuilds mIndications + the visible rows based on searchText.
		  // Empty searchText = show everything. Otherwise case-insensitive
		  // substring match on Title OR Keywords.
		  mIndications.ResizeTo(-1)
		  Self.lstIndications.RemoveAllRows
		  If Self.lstIndications.SectionCount = 0 Then
		    Self.lstIndications.AddSection("")
		  End If

		  Var q As String = searchText.Trim.Lowercase

		  For Each ind As Indication In mAllIndications
		    Var match As Boolean = (q = "") _
		      Or (ind.Title.Lowercase.IndexOf(q) >= 0) _
		      Or (ind.Keywords.Lowercase.IndexOf(q) >= 0)
		    If match Then
		      mIndications.Add ind
		      Var cell As MobileTableCellData = Self.lstIndications.CreateCell
		      cell.Text = ind.Title
		      cell.DetailText = ind.SourceList
		      Self.lstIndications.AddRow(0, cell)
		    End If
		  Next
		End Sub


	#tag EndMethod


	#tag Enum, Name = EntryModes, Type = Integer, Flags = &h0
		Browse
		  Search
	#tag EndEnum

	#tag Property, Flags = &h21
		Private mAllIndications() As Indication
	#tag EndProperty

	#tag Property, Flags = &h21
		Private mIndications() As Indication
	#tag EndProperty

	#tag Property, Flags = &h21
		Private mEntryMode As EntryModes = EntryModes.Browse
	#tag EndProperty
#tag EndWindowCode

#tag Events lstIndications
	#tag Event
		Sub SelectionChanged(section As Integer, row As Integer)
		  #Pragma Unused section
		  If row < 0 Or row > mIndications.LastIndex Then Return
		  Var detail As New scIndicationDetail
		  detail.SetIndication(mIndications(row))
		  Self.PushTo(detail)
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
