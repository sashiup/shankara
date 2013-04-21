$(document).ready(function() {
    //checks for the button click event
    $("#getFeed").click(function(e){
           
            //get the form data and then serialize that
            dataString = $("#myAjaxRequestForm").serialize();
            //get the form data using another method 
            var feedString = $("input#feedName").val();
            dataString = "feedurl=" + feedString+"&reqType="+"FEED";
            
            //make the AJAX request, dataType is set to json
            //meaning we are expecting JSON data in response from the server
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/shankaraProto/EmployeeProfile",
                data: dataString,
                dataType: "json",
                
                //if received a response from the server
                success: function( data, textStatus, jqXHR) {
                    //our country code was correct so we have some information to display
                     if(data.success){
                         $("#feedResponse").html("");
                         $("#feedResponse").append("<b>TotalEntries:</b> " + data.sfeedObj.firstName + "<br/>");
                         $("#feedResponse").append("<b>Title:</b> " + data.sfeedObj.title + "<br/>");
                         $("#feedResponse").append("<b>Author:</b> " + data.sfeedObj.author+ "<br/>");
                         $("#feedResponse").append("<b>Content:</b> " + data.sfeedObj.content+ "<br/>");
                     } 
                     //display error message
                     else {
                         $("#feedResponse").html("<div><b>No Data Found!</b></div>");
                     }
                },
                
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#feedResponse").html(jqXHR.responseText);
                },
                
                //capture the request before it was sent to server
                beforeSend: function(jqXHR, settings){
                    //adding some Dummy data to the request
                    settings.data += "&dummyData=whatever";
                    //disable the button until we get the response
                    $('#myButton').attr("disabled", true);
                },
                
                //this is called after the response or error functions are finsihed
                //so that we can take some action
                complete: function(jqXHR, textStatus){
                    //enable the button 
                    $('#myButton').attr("disabled", false);
                }
      
            });        
    });
 
});