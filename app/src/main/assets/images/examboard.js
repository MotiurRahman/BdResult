function fd()
{
	if(result.exam.value == "ssc_voc" || 
	   result.exam.value == "hsc_voc" ||
	   result.exam.value == "hsc_hbm" ||
	   result.exam.value == "hsc_dic")
	{
		result.board.value = "tec";
	}

	if(result.exam.value == "hsc_dibs")
	{
		result.board.value = "dibs";
	}

}