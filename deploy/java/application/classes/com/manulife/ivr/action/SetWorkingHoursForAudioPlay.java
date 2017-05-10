package com.manulife.ivr.action;



import com.audium.server.AudiumException;
import com.audium.server.proxy.ActionConfigInterface;
import com.audium.server.session.ActionAPI;
import com.audium.server.xml.ActionElementConfig;

public class SetWorkingHoursForAudioPlay implements ActionConfigInterface{
	
	@Override
	public ActionElementConfig getConfig(String name, ActionAPI actionAPI,
			ActionElementConfig actionElementConfig) throws AudiumException {
		// TODO Auto-generated method stub
		 if(actionElementConfig == null)
			 actionElementConfig = new ActionElementConfig();
			 
		 try {
 				
			 String dates = actionAPI.getElementData("Working Hour_Lookup", "date");
			 String startTimes = actionAPI.getElementData("Working Hour_Lookup", "startTime");
			 String endTimes = actionAPI.getElementData("Working Hour_Lookup", "endTime");

			 //dates = "Monday/Tuesday/";
			 //startTimes= "09:30/08:45/";
			 //endTimes= "19:30/18:45/";

			 String split =":::|||";
			 
			 String voicefileNames ="";

             if(null != dates && null != startTimes && null != endTimes ){
    			 
				 
				 String[] dateList = dates.split("/");
				 String[] startTimeList = startTimes.split("/");
				 String[] endTimeList = endTimes.split("/");
	
				 if(dateList.length>0){
					 if(dateList.length == startTimeList.length && dateList.length == endTimeList.length){
						 
						 for(int i=0; i< dateList.length; i++){
							 if(voicefileNames.length()==0)
								 voicefileNames= dateList[i];
							 else
								 voicefileNames = voicefileNames + split + dateList[i];
							 
							 if(voicefileNames.length()>0){
								 if(null != startTimeList[i] && startTimeList[i].length()>0){
									 String[] startTime1s = startTimeList[i].split(":");
								 		if(startTime1s[0].startsWith("0"))
								 			voicefileNames = voicefileNames + split + startTime1s[0].substring(1,2);
								 		else
								 			voicefileNames = voicefileNames + split + startTime1s[0];
								 		if(startTime1s[1].startsWith("0"))
								 			voicefileNames = voicefileNames + split + startTime1s[1].substring(1,2);
								 		else
								 			voicefileNames = voicefileNames + split + startTime1s[1];
								 	}
								 
								 if(null != endTimeList[i] && endTimeList[i].length()>0){
									 String[] endtimes = endTimeList[i].split(":");
								 	if(endtimes[0].startsWith("0"))
								 		voicefileNames = voicefileNames + split + endtimes[0].substring(1,2);
								 	else
								 		voicefileNames = voicefileNames + split + endtimes[0];
								 	if(endtimes[1].startsWith("0"))
								 		voicefileNames = voicefileNames + split + endtimes[1].substring(1,2);
								 	else
								 		voicefileNames = voicefileNames + split + endtimes[1];
								 }
							 }
						 
						 }
						 voicefileNames = voicefileNames+":::"; 
					 
					 }
				 }
             }
			 
			 
 				actionAPI.setElementData("WorkingHoursVoiceFileNames", voicefileNames);
 				//actionAPI.getLogger("").
 //				System.out.println("WorkingHoursVoiceFileNames=" + voicefileNames);
 				actionAPI.addToLog("WorkingHoursVoiceFileNames", voicefileNames);
	 		} catch( Exception e){
	 			e.printStackTrace();
	 		}
	 
		 
		 
		 
        // Alter defaults or create a new configuration and return.
        return actionElementConfig;

	}
	
	
	/*
	@Override
	public ActionElementConfig getConfig(String name, ActionAPI actionAPI,
			ActionElementConfig actionElementConfig) throws AudiumException {
		// TODO Auto-generated method stub
		 if(actionElementConfig == null)
			 actionElementConfig = new ActionElementConfig();
			 
		 try {
			 	String countSt =(String)actionAPI.getSessionData("count");
			 	if(countSt.contains("."))
			 		countSt = countSt.substring(0, countSt.indexOf("."));
			 
			 	int count = Integer.parseInt(countSt);
 
				 String dates = actionAPI.getElementData("Working Hour_Lookup", "date");
				 String startTimes = actionAPI.getElementData("Working Hour_Lookup", "startTime");
				 String endTimes = actionAPI.getElementData("Working Hour_Lookup", "endTime");

				 String[] dateList = dates.split("/");
				 String[] startTimeList = startTimes.split("/");
				 String[] endTimeList = endTimes.split("/");
				 
				 int length = dateList.length;
				 if(dateList.length == startTimeList.length && dateList.length== endTimeList.length){
					 
					 if(count-1<length) {
						 actionAPI.setElementData("WorkingDate", dateList[count-1]);
						 actionAPI.setElementData("WorkingDateStartTime", startTimeList[count-1]);
						 actionAPI.setElementData("WorkingDateEndTime", endTimeList[count-1]);
					 } 
					 if(count == length) {
						 actionAPI.setElementData("LastWorkingDateTime","Y");
					 }
					 
				 }
 				
 				
	 		} catch( Exception e){
	 			e.printStackTrace();
	 		}
	 
	 
        // Alter defaults or create a new configuration and return.
        return actionElementConfig;

	}
	*/
	
}
