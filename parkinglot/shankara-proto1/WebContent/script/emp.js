$(document).ready(function() {
 
    //Stops the submit request
    $("#myAjaxRequestForm").submit(function(e){
           e.preventDefault();
    });
    
    //checks for the button click event
    $("#myButton").click(function(e){
           
            //get the form data and then serialize that
            dataString = $("#myAjaxRequestForm").serialize();
            //get the form data using another method 
            var lastName = $("input#emplastNm").val();
            var fstName = $("input#empfstNm").val();
            dataString = "lstName=" + lastName+"&fstName="+fstName+"&reqType="+"EMP";
            
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
                         $("#empResponse").html("");
                         $("#empResponse").append("<b>FirstName:</b> " + data.empInfo.firstName + "<br/>");
                         $("#empResponse").append("<b>LastName:</b> " + data.empInfo.lastName + "<br/>");
                         $("#empResponse").append("<b>Title:</b> " + data.empInfo.title+ "<br/>");
                     } 
                     //display error message
                     else {
                         $("#empResponse").html("<div><b>No Data Found!</b></div>");
                     }
                },
                
                //If there was no resonse from the server
                error: function(jqXHR, textStatus, errorThrown){
                     console.log("Something really bad happened " + textStatus);
                      $("#empResponse").html(jqXHR.responseText);
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