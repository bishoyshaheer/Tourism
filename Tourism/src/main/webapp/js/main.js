(function ($) {

//global client 
    var gClient;
    //init the citysdk client
    $(document).ready(function () {
        initCityUri("http://tourism.citysdk.cm-lisboa.pt/");
    });
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.scrollup').fadeIn();
        } else {
            $('.scrollup').fadeOut();
        }
    });
    $('.scrollup').click(function () {
        $("html, body").animate({scrollTop: 0}, 1000);
        return false;
    });
    // local scroll
    jQuery('.navbar').localScroll({hash: true, offset: {top: 0}, duration: 800, easing: 'easeInOutExpo'});
    // portfolio
    function protfolioOnClickCategories() {
        if ($('.isotopeWrapper').length) {

            $('#filter a').click(function () {

                console.log($(this));
                $('#filter a').removeClass('current');
                $(this).addClass('current');
                //query the citySdk for 9 pois
                $category = $(this).text();
                console.log($category);
                var mClient = gClient,
                        parameters = {
                            'category': $category,
                            'limit': 12
                        };
                mClient.useVersion("1.0");
                try {
                    mClient.getPois(parameters, handleCategory, handleError);
                } catch (e) {
                    console.log(e);
                }

                return false;
            });
        }


    }
    //search using minimal
    $("#search").submit(searchHandle);

    //search handler function
    function searchHandle(event) {
        event.preventDefault();
        $searchData = $("#searchData").val();
        var mClient = gClient,
                parameters = {
                    'complete': $searchData,
                    'limit': 12
                };
        mClient.useVersion("1.0");
        try {
            mClient.getPois(parameters, handleCategory, handleError);
        } catch (e) {
            console.log(e);
        }
    }

    //search auto complete

    //a function to render a choosen category 
    //where data is an object that has a property (array of pois)
    function handleCategory(data, textStatus, jqXHR) {
        map.removeMarkers();
        console.log(data);
        //use 9 tiles around the map
        $('#6').html("");
        jQuery.each(data.poi, function (index, value) {
            $point = DataReader.getLocationPoint(value, term.POINT_TERM_ENTRANCE);
            $label = DataReader.getLabel(value, term.LABEL_TERM_PRIMARY, "pt-PT");
            $imagesUri = DataReader.getImagesUri(value);
            $imageUri = "img/portfolio/img1.jpg";
            console.log($imagesUri);
            $lat = "";
            $long = "";
            if ($point.length > 0) {
                $lat = $point[0].getLocation().getLatitude();
                $long = $point[0].getLocation().getLongitude();
                map.addMarker({
                    lat: $lat,
                    lng: $long,
                    title: $label,
                    infoWindow: {
                        content: '<p>' + $label + '</p>'
                    }
                });
            }
            if ($imagesUri.length > 0 && $imagesUri[0].hasImgUri()) {
                $imageUri = $imagesUri[0].getContent();
                console.log($imageUri);
            }
            $('#6').append('<article class="col-md-3"><input type="hidden" class="lat" value="' + $lat + '" /><input type="hidden" class="long" value="' + $long + '" /><div class="portfolio-item"><img src="' + $imageUri + '" alt="" /><div class="portfolio-desc align-center"><div class="folio-info"><h5><a href="#">' + $label + '</a></h5><a href="' + $imageUri + '" class="fancybox"><i class="fa fa-plus fa-2x"></i></a></div></div></div></article>');
        });
    }

    // fancybox
    jQuery(".fancybox").fancybox();
    if (Modernizr.mq("screen and (max-width:1024px)")) {
        jQuery("body").toggleClass("body");
    } else {
        var s = skrollr.init({
            mobileDeceleration: 1,
            edgeStrategy: 'set',
            forceHeight: true,
            smoothScrolling: true,
            smoothScrollingDuration: 300,
            easing: {
                WTF: Math.random,
                inverted: function (p) {
                    return 1 - p;
                }
            }
        });
    }



    //scroll menu
    jQuery('.appear').appear();
    jQuery(".appear").on("appear", function (data) {
        var id = $(this).attr("id");
        jQuery('.nav li').removeClass('active');
        jQuery(".nav a[href='#" + id + "']").parent().addClass("active");
    });
    //parallax
    var isMobile = false;
    if (Modernizr.mq('only all and (max-width: 1024px)')) {
        isMobile = true;
    }


    if (isMobile == false && ($('#parallax1').length || isMobile == false && $('#parallax2').length || isMobile == false && $('#testimonials').length))
    {


        $(window).stellar({
            responsive: true,
            scrollProperty: 'scroll',
            parallaxElements: false,
            horizontalScrolling: false,
            horizontalOffset: 0,
            verticalOffset: 0
        });
    }

    //nicescroll
    $("html").niceScroll({zindex: 999, cursorborder: "", cursorborderradius: "2px", cursorcolor: "#191919", cursoropacitymin: .5});
    function initNice() {
        if ($(window).innerWidth() <= 960) {
            $('html').niceScroll().remove();
        } else {
            $("html").niceScroll({zindex: 999, cursorborder: "", cursorborderradius: "2px", cursorcolor: "#191919", cursoropacitymin: .5});
        }
    }
    $(window).load(initNice);
    $(window).resize(initNice);
    function initCityUri(uri) {
        // handleVisit is the callback for a success call, handleError for error situations
        TourismVisitor.visit(uri, handleVisit, handleError);
    }

    // method to handle the erroneous calls
    // its parameters are similar to an Ajax call
    function handleError(jqXHR, textStatus, errorThrown) {
        console.log(jqXHR, errorThrown, textStatus);
    }

    // method to handle the data
    // in this case it will receive the client as a first parameter
    function handleVisit(client, textStatus, jqXHR) {
        gClient = client;
        var mClient = client,
                parameters = {
                    'list': parameterTerms.POIS,
                    'limit': "10"
                };
        mClient.useVersion("1.0")
        try {
            mClient.getCategories(parameters, handleCategories, handleError);
        } catch (e) {
            console.log(e);
        }

    }
    function handleCategories(data, textStatus, jqXHR) {
        var categories = [];
        jQuery.each(data.categories, function (index, value) {
            categories[index] = DataReader.getLabel(data.categories[index], term.LABEL_TERM_PRIMARY, "pt-PT");
            $("#ulCategories").append('<li><a href="#"  class="btn-theme btn-small" >' + categories[index] + '</a></li>');
        });
        protfolioOnClickCategories();
    }
})(jQuery);