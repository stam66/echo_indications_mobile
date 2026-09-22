#tag Class
Protected Class Indication
	#tag Method, Flags = &h0
		Shared Function FromJSON(j As JSONItem) As Indication
		  // Parse a JSON object (one indication) from the server into an Indication.
		  // Handles JSON null for nullable fields ("" / 0 sentinels in the model).
		  Var ind As New Indication

		  ind.ID = j.Lookup("id", 0).IntegerValue
		  ind.Title = j.Lookup("title", "").StringValue
		  ind.Keywords = j.Lookup("keywords", "").StringValue
		  ind.Comments = j.Lookup("comments", "").StringValue

		  // Nullable enum fields — server sends JSON null when unset
		  ind.PrimaryCare = StringOrEmpty(j, "primary_care")
		  ind.SecondaryInpatient = StringOrEmpty(j, "secondary_inpatient")
		  ind.SecondaryOutpatient = StringOrEmpty(j, "secondary_outpatient")
		  ind.Urgency = StringOrEmpty(j, "urgency")

		  ind.SourceASE = j.Lookup("source_ase", False).BooleanValue
		  ind.SourceEACVI = j.Lookup("source_eacvi", False).BooleanValue
		  ind.SourceBSE = j.Lookup("source_bse", False).BooleanValue
		  ind.SourceBHVS = j.Lookup("source_bhvs", False).BooleanValue
		  ind.SourceConsensus = j.Lookup("source_consensus", False).BooleanValue

		  // Contexts: array of {id, name}
		  If j.HasName("contexts") Then
		    Var contexts As JSONItem = j.Value("contexts")
		    For i As Integer = 0 To contexts.Count - 1
		      Var c As JSONItem = contexts.Child(i)
		      ind.ContextIDs.Add c.Lookup("id", 0).IntegerValue
		      ind.ContextNames.Add c.Lookup("name", "").StringValue
		    Next
		  End If

		  Return ind
		End Function
	#tag EndMethod

	#tag Method, Flags = &h21
		Private Shared Function StringOrEmpty(j As JSONItem, key As String) As String
		  // Returns "" for missing-or-null, the string value otherwise.
		  If Not j.HasName(key) Then Return ""
		  Var v As Variant = j.Value(key)
		  If v Is Nil Or v.IsNull Then Return ""
		  Return v.StringValue
		End Function
	#tag EndMethod

	#tag Method, Flags = &h0
		Function SourceList() As String
		  // Human-readable comma-separated list of which sources back this indication.
		  Var parts() As String
		  If Self.SourceASE Then parts.Add "ASE"
		  If Self.SourceEACVI Then parts.Add "EACVI"
		  If Self.SourceBSE Then parts.Add "BSE"
		  If Self.SourceBHVS Then parts.Add "BHVS"
		  If Self.SourceConsensus Then parts.Add "Consensus"
		  Return String.FromArray(parts, ", ")
		End Function
	#tag EndMethod


	#tag Property, Flags = &h0
		ID As Integer
	#tag EndProperty

	#tag Property, Flags = &h0
		Title As String
	#tag EndProperty

	#tag Property, Flags = &h0
		Keywords As String
	#tag EndProperty

	#tag Property, Flags = &h0
		Comments As String
	#tag EndProperty

	#tag Property, Flags = &h0
		PrimaryCare As String
	#tag EndProperty

	#tag Property, Flags = &h0
		SecondaryInpatient As String
	#tag EndProperty

	#tag Property, Flags = &h0
		SecondaryOutpatient As String
	#tag EndProperty

	#tag Property, Flags = &h0
		Urgency As String
	#tag EndProperty

	#tag Property, Flags = &h0
		SourceASE As Boolean
	#tag EndProperty

	#tag Property, Flags = &h0
		SourceEACVI As Boolean
	#tag EndProperty

	#tag Property, Flags = &h0
		SourceBSE As Boolean
	#tag EndProperty

	#tag Property, Flags = &h0
		SourceBHVS As Boolean
	#tag EndProperty

	#tag Property, Flags = &h0
		SourceConsensus As Boolean
	#tag EndProperty

	#tag Property, Flags = &h0
		ContextIDs() As Integer
	#tag EndProperty

	#tag Property, Flags = &h0
		ContextNames() As String
	#tag EndProperty


End Class
#tag EndClass
