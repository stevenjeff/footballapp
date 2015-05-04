/**
 * 				// form here
 * 				$("#test").formValidator({
 * 					// optional, if form can submit, return true, otherwise return false
 * 					beforeSubmit : function() {return true;},
 *					fields: {
 *  					CaseNumber: {
 *  						 validators: {
 *	 							notEmpty: {
 *	 		                        message: 'CaseNumber is required and cannot be empty'
 *	 		                    }
 *	 						 }
 *	 					},
 *	 					FirstName : {
 *	 						validators: {
 *	 							stringLength : {
 *	 								min: 6,
 *	 		                        max: 30,
 *	 		                        message: 'The first name must be more than 6 and less than 30 characters long'
 *	 							}
 *	  						}
 *	  					},
 *					    input1 :{
 *							validators: {
 *								extCheck : {
 *									// if validate fail return true, if validate pass return false
 *									extFun: function () {return true;},
 *									message : 'input1 must not the same as input2'
 *								}
 *							}
 *						},
 *						// if input name like 'searchform.a', please use ''
 *						'searchform.a':{
 *							validators: {
 *								notEmpty: {
 *					                message: 'searchform.a is required and cannot be empty',
 *					            }
 *							}
 *						}
 *				}
 *			});
 * 
 */

(function($) {
	
	var V = $.fn.formValidator = function(options) {
	
		var formObj = this;
		formObj.on('submit', function(e) {
			// 1.check before submit
			var canSubmit = beforeSubmit(options);
			if(!canSubmit){return false;}
			// 2.check all input values
			var result = checkFields(formObj, options);
			// 3.if check failed
			if(formCanSubmit(result)){
				formObj.find('button[type="submit"]').attr('disabled', 'disabled');
				return true;
			}
			return false;
		});
		
		return this.clearOnReset();
	};
	
	var R = $.fn.clearOnReset = function(){
		
		var formObj = this;
		formObj.on('reset', function(e){
			formObj.find("input,textarea").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked");
			formObj.find("select").find("option").removeAttr("selected");
			// fix for IE9, under IE9 remove all selected will not select the first one, we must set it manually
			formObj.find("select").each(function(){
				var selObj = $(this);
				// if not multiple, select the first option
				if(!selObj.attr("multiple")){
					selObj.find("option:first").attr("selected", "selected");
				}
			})
			return false;
		});
		
		return this;
	};
	
	function beforeSubmit(options){
		if(options && $.isFunction(options.beforeSubmit)){
			return options.beforeSubmit();
		}
		return true;
	};
	
	function checkFields(formObj, options){
		clearError(formObj);
		var result = 1;
		if(options && options.fields){
			var fields = options.fields;
			for(var name in fields){
				var fieldsDef = fields[name];
				result *= validateFields(formObj, name, fieldsDef);
			}
		}
		return result;
	}; 
	
	function formCanSubmit(result){
		var allValid = 1;
		return result == allValid;
	};
		
	function clearError(formObj){
		formObj.find("span.help-block").remove();
		formObj.find("div.has-error").removeClass("has-error");
		$("div.bs-callout.bs-callout-danger").hide();
	};
	
	// one fields may have multiple validators
	function validateFields(formObj,name,fieldsDef){
		var validInput = true;
		var validators = fieldsDef.validators;
		if(validators){
			// if input , checkbox group, textarea
			var inputObjs =  formObj.find("input[name='"+name+"'], textarea[name='"+name+"'], select[name='"+name+"']");
			for(var valiName in validators){
				var fieldsVal = getFieldVal(inputObjs);
				var validator = validators[valiName];
				validator.name = valiName;
				validInput = switchToValid(validator,fieldsVal);
				if(!validInput){displayError(validator,inputObjs);break;}
			}
		}
		return validInput ? 1:0;
	};
	
	function getFieldVal(inputObjs){
		if(!inputObjs[0]){error("can't find inputObjs " + inputObjs.selector);}
		var vals = [];
		// for checkbox group
		if(inputObjs && inputObjs.length > 1){
			inputObjs.each(function(){
				var thisCheckBox = $(this);
				if(thisCheckBox.is(':checked')){
					vals.push(thisCheckBox.val());	
				}
			});
		}
		// for multiple select
		else if(inputObjs.is('select') && inputObjs.attr("multiple")){
			inputObjs.find("option:selected").each(function(){
				vals.push($(this).val());	
			});
		}
		// for single input and select
		else{
			vals.push(inputObjs.val());
		}
		log(inputObjs[0].name + " value is " +vals.join(","));
		return $.trim(vals.join(","));
	};
	
	function displayError(validator,inputObjs){
		var inputParent = findParentDiv(inputObjs);
		inputParent.append("<span class='help-block'>"+validator.message+"</span>");
		inputParent.addClass("has-error");
	};
	
	// only for datapicker div
	function findParentDiv(inputObjs){
		var parent = $(inputObjs[0]).parent();
		if(parent.hasClass("datepicker")){
			parent = parent.parent();
		}
		return parent;
	};
	
	/**
	 * valid   input return true;
	 * invalid input return false;
	 */
	function switchToValid(validator,fieldsVal){
		var vName = validator.name;
		if("notEmpty" == vName){
			return !isEmpty(fieldsVal,validator);
		}else if("stringLength" == vName){
			return !invalidLength(fieldsVal,validator);
		}else if ("extCheck" == vName){
			return !invalidExtCheck(fieldsVal,validator);
		}
		return true;
	};
	
	function invalidExtCheck(fieldsVal,validator){
		return $.isFunction(validator.extFun) ? validator.extFun(): false;
	};
	
	function isEmpty(fieldsVal,validator){
		return typeof(validator.emptyVal) == "undefined" ? (fieldsVal.length == 0) : (fieldsVal == validator.emptyVal) ;
	};
	
	function invalidLength(fieldsVal,validator){
		return (fieldsVal.length > validator.max) || (fieldsVal.length < validator.min);
	};
	
	function log(str){
		//if(window.console){console.log(str);}
	};
	
	function error(str){
		//if(window.console){console.error(str);}
	};
	
})(jQuery);  
