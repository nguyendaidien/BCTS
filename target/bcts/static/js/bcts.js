var $ = jQuery;
//var contextPath='/bcts';

$(document).ready(function() {	
		console.log("getMAX_REQUEST_SIZE: " + getMAX_REQUEST_SIZE);
//		console.log("getMAX_REQUEST_SIZE2: " + getMAX_REQUEST_SIZE2);
		var MAX_REQUEST_SIZE = $("#MAX_REQUEST_SIZE").val();
		console.log("MAX_REQUEST_SIZE: " + MAX_REQUEST_SIZE);
		$('#permitNoSearch').autocomplete({
				minLength: 1,
		        delay: 500,
		        //define callback to format results
		        source: function (request, response) {
		            $.getJSON(contextPath + "/permit/search", request, function(result) {
		                response($.map(result, function(item) {
		                    return {
		                    	label: item.permitNo,		                    
		                        // following property gets entered in the textbox
		                        value: item.permitNo,
		                    }
		                }));
		            });
		        },
		      	//define select handler
		        select : function(event, ui) {
		            if (ui.item) {
		                event.preventDefault();
		                //var permitNo = ui.item.value;
		                $("#permitNo").val(ui.item.value);
		                console.log("selected Permit No: " + ui.item.value);
		                //$("#tagQuery").value = $("#tagQuery").defaultValue
		                //var defValue = $("#permitNoSearch").prop('defaultValue');
		                $("#permitNoSearch").val(ui.item.value);
		                $("#permitNoSearch").blur();
		                window.location.href = contextPath+"/docs/manage/" + ui.item.value;
		                //return false;
		            }
		        }
		});
		
//		document.getElementById("file").onchange = function() {			
//			
//		    document.getElementById("formUpload").submit(function() {
//		    	   return $('form[id="formUpload"]').validate({ 
//		    		   rules: {
//		   		    	file: {
//		   		            required: true,
//		   		            extension: "docx|rtf|doc|pdf"
//		   		        }
//		   		    },
//		   		    messages: {
//		   		    	file: {
//		   		            required: "Please select a file",
//		   		            extension: "Please upload valid file formats"
//		   		        }
//		   		    }
//		    	   });
//		    });
//		};
		// var form= document.getElementById("formUpload");
//		document.getElementById("file").onchange = function() {	
//			$('form[id="formUpload"]').submit(validate_and_submit());
//		};
		
		var getFileUploadLimit = function(){
				var totalFilesSize = $('#totalFilesSize').val() !='' ? parseFloat($('#totalFilesSize').val()) : 0;
				var limitUploadSize = 1024*1000;//1M
				var fileLimit = limitUploadSize - totalFilesSize*1024;
				console.log("fileLimit: " + fileLimit);
				return fileLimit;
		};

		$.validator.addMethod('filesize', function (value, element, arg) {
            var minsize=1000; // min 1kb
            return this.optional(element) || (element.files[0].size <= arg);
        });
		$.validator.addMethod('permitNoRequired', function (value, element, arg) {          
			var val = element.form.elements['permitNo'].value;
            return val !='';
        });
		
		$('form[id="formUpload"]').validate({
			ignore: 'hidden',
		    rules: {
		    	permitNo:{
		    		permitNoRequired: true
		    	},		    
		    	file: {
		            required: true,
		            extension: "docx|rtf|doc|pdf",
		            filesize: getFileUploadLimit()
		        }
		    },
		    messages: {
		    	permitNo:{
		    		permitNoRequired: "Please select a permit No. first"
		    	},	
		    	file: {
		            required: "Please select a file",
		            extension: "Please upload valid file formats",
		            filesize:"Exceeded total upload size limit."
		        }
		    }
//		    submitHandler : function(form) {
//		        //do something here
//		        form.submit();
//		    }
		});
		
		$(".dropdown-menu li a").click(function(){
			$(this).parents(".dropdown").find('.btn').html($(this).text()) ;
			  $(this).parents(".dropdown").find('.btn').val($(this).data('value'));
			  $("#docType").val($(this).data('value'));
		   });
});
