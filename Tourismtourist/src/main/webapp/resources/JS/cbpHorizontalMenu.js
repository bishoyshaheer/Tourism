/**
 * cbpHorizontalMenu.js v1.0.0
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2013, Codrops
 * http://www.codrops.com
 */
 var cbpHorizontalMenu = (function() {

 	var $listItems = $( '#cbp-hrmenu > ul > li' ),
 	$menuItems = $listItems.children( 'a' ),
 	$body = $( 'body' ),
 	current = -1;

 	function init() {
 		$menuItems.on( 'click', open );
 		$listItems.on( 'click', function( event ) { event.stopPropagation(); } );
 	}

 	function open( event ) {
            
//            set subcategories with class cbp-ig-grid
              $(".cbp-hrsub-inner > ul").removeClass().addClass("cbp-ig-grid");

 		if( current !== -1 ) {
 			$listItems.eq( current ).removeClass( 'cbp-hropen' );
 		}

 		var $item = $( event.currentTarget ).parent( 'li' ),
 		idx = $item.index();

 		if( current === idx ) {
 			// $item.removeClass( 'cbp-hropen' );
 			// current = -1;
 			close();
 		}
 		else {
 			$item.addClass( 'cbp-hropen' );
 			current = idx;


 			$("#cbp-hrmenu").position({
 			"my": "center center",
 			"at": "center top",
 			"of": $("#cbp-bicontrols"),
 			using: function( position ) {
 				$( this ).animate( position );
 			}
 		});

 			$body.off( 'click' ).on( 'click', close );
 		}
 		// console.log($(this));
 		
 		return false;

 	}

 	function close( event ) {
 		$listItems.eq( current ).removeClass( 'cbp-hropen' );
 		current = -1;
 		$("#cbp-hrmenu").position({
 			"my": "center center",
 			"at": "center bottom",
 			"of": window,
 			using: function( position ) {
 				$( this ).animate( position );
 			}
 		});
 	}

 	return { init : init };

 })();