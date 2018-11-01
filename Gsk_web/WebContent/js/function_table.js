// Code goes here

/*window.onload = function(){
	  var tableCont = document.querySelector('#table-cont')

	  function scrollHandle (e){
	    var scrollTop = this.scrollTop;
	    //this.querySelector('#thea').style.transform = 'translateY(' + scrollTop + 'px)';
	    //this.querySelector('#thea').style.-ms-transform = 'rotateY(' + scrollTop + 'px)';
	    //$('#thea').css("display",'block');
	    //$('#thea').css("position",'fixed');
	    //$('#thea').css("transform",'translateY(' + scrollTop + 'px)');
	   // $('#thea').css("-ms-transform",'translate("0",' + scrollTop + 'px)');
	  }
	tableCont.addEventListener('scroll',scrollHandle);
}*/
window.onload = function(){
	  var tableCont = document.querySelector('#table-cont')

	  function scrollHandle (e){
	    var scrollTop = this.scrollTop;
	    /*if(scrollTop >= 40){
    		if(scrollTop % 40 > 20){
	    		var temp1 = Math.round(scrollTop / 40) * 40 ;
	    		
	    		$("#table-cont").scrollTop(temp1 );	

    		}else{
	    		var temp2 = Math.round(scrollTop / 40) * 40 ;
	    		
	    		$("#table-cont").scrollTop(temp2 );   	

    		}
    	}  */
	  }
	tableCont.addEventListener('scroll',scrollHandle);
}
/*  var tableCont = document.querySelector('#table-cont')
  var num = 0
  function scrollHandle (e){
    var scrollTop = this.scrollTop;
    //走一次了
    if(num == 0){
    	num++ ;
    	$("#thea").css("transform",'translateY(' + scrollTop  + 'px)');
    //第二次了
    }else if(num == 1){
		num ++ ;
    	if(scrollTop >= 40){
    		if(scrollTop % 40 > 20){
	    		var temp1 = Math.round(scrollTop / 40) * 40 ;
	    		
	    		$("#table-cont").scrollTop(temp1 + 80);	
	    		$("#thea").css("transform",'translateY(' + temp1 + 80 + 'px)');	
    		}else{
	    		var temp2 = Math.round(scrollTop / 40) * 40 ;
	    		
	    		$("#table-cont").scrollTop(temp2 + 80);   	
	    		$("#thea").css("transform",'translateY(' + temp2 + 80 + 'px)');
    		}
    	}   	
    }else if(num == 2){
    	num = 0;
    	return ;
    }
  }
  
  tableCont.addEventListener('scroll',scrollHandle);
  */
/* $("#table-cont").scroll(function() {
   var scrollTop = this.scrollTop;
	//如果下滑除上40
	if(scrollTop >= 40){
		if(scrollTop % 40 > 20){
		var temp1 = Math.round(scrollTop / 40) * 40 ;
		$("#table-cont").scrollTop(Math.round(temp1) + 80);
		$("#thea").css("transform",'translateY(' + temp1 + 80 + 'px)');			
		}else{
		var temp2 = Math.round(scrollTop / 40) * 40 ;
		$("#table-cont").scrollTop(Math.round(temp2) + 80);
		$("#thea").css("transform",'translateY(' + temp2 + 80 + 'px)');		
		}
	}else{
		$("#thea").css("transform",'translateY(' + scrollTop + 'px)');
	}
	//setTimeout(function(){},1000);
});*/
