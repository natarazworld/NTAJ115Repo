

/*function validate(frm){
	
	//read form data
	var name=frm.pname.value;
	var  age=frm.page.value;
	var gender=frm.gender.value;
	var flag=true;
	//Client Side form validaton logics
	if(name==""){  //required rule
		alert("Person name is required");
		flag=false;
	} 
	else if(name.length<5){ //min length rule
		alert("Person  name must have minimum of 5 characters");
		flag=false;
	}
	
	if(age==""){
		alert("Person age is required");
		flag=false;
	}
	else if(isNaN(age)){
		alert("Person age must be numeric value");
		flag=false;
	}
	else if(age<=0 || age>125){
		alert("Person age must be there in the range of 1 through 125");
		flag=false;
	}
	
	if(gender==""){
		alert("Person gender  must be SELECTED");
		flag=false;
	}
	 if(flag==false)
        frm.pname.focus();
	return flag;
}//function*/

function validate(frm){
	
	//read form data
	var name=frm.pname.value;
	var  age=frm.page.value;
	var gender=frm.gender.value;
	var flag=true;
	//Client Side form validaton logics
	if(name==""){  //required rule
		alert("Person name is required");
		flag=false;
	} 
	else if(name.length<5){ //min length rule
		alert("Person  name must have minimum of 5 characters");
		flag=false;
	}
	
	if(age==""){
		alert("Person age is required");
		flag=false;
	}
	else if(isNaN(age)){
		alert("Person age must be numeric value");
		flag=false;
	}
	else if(age<=0 || age>125){
		alert("Person age must be there in the range of 1 through 125");
		flag=false;
	}
	
	if(gender==""){
		alert("Person gender  must be SELECTED");
		flag=false;
	}
	 if(flag==false)
        frm.pname.focus();
	return flag;
}