$(document).ready(function () {
    // Lấy productId từ URL
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get("productId");

    // Function to add a product to the cart
    function addToCart() {
        // Get token from Local Storage
        var token = localStorage.getItem("token");

        // Check if the token exists
        if (token) {
            // Send token to the endpoint /user/decode-token for decoding
            $.ajax({
                url: "http://localhost:8080/user/decode-token",
                method: "post",
                data: {
                    token: token
                }
            }).done(function (data) {
                // Check the returned data
                if (data && data.userId) {
                    // Get quantity from the input field
                    var quantity = parseInt($(".num-product").val());

                    // Create a cart request object
                    const cartRequest = {
                        idProduct: parseInt(productId),
                        idUser: data.userId,
                        quantity: quantity
                        // You may add other fields if needed
                    };

                    // Send a request to the /cart endpoint to add the product to the cart
                    $.ajax({
                        url: "http://localhost:8080/cart",
                        method: "post",
                        contentType: "application/json",
                        data: JSON.stringify(cartRequest),
                    }).done(function (response) {
                        if (response && response.statusCode === 200) {
                            // Handle success (you may update the cart display or show a success message)
                            alert("Product added to cart successfully!");
                        } else {
                            // Handle failure
                            alert("Failed to add product to cart");
                        }
                    }).fail(function () {
                        // Handle failure
                        alert("Failed to add product to cart");
                    });

                } else {
                    // Handle the case where userId is not available
                    alert("Failed to get user ID");
                }
            }).fail(function () {
                // Handle the case where decoding the token fails
                alert("Failed to decode token");
            });

        } else {
            // Handle the case where the token is not found in Local Storage
            alert("Token not found in Local Storage");
        }
    }

    $.ajax({
        url: `http://localhost:8080/product/${productId}`, // Truy vấn sản phẩm chi tiết dựa trên productId
        method: "get",
    }).done(function (data) {
        console.log("server tra ve ", data);
        const element = document.getElementById("productDetail");
        let htmlAdd = "";
        const product = data.data[0];
        if (product) {
            htmlAdd = `
                <div class="row" >
                            <div class="col-md-6 col-lg-7 p-b-30">
                                <div class="p-l-25 p-r-30 p-lr-0-lg">
                                    <div class="wrap-slick3 flex-sb flex-w">
                                        <div class="wrap-slick3-dots"></div>
                                        <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>
                                        <div class="slick3 gallery-lb">
                                            <div class="item-slick3" data-thumb="images/${product.image}">
                                                <div class="wrap-pic-w pos-relative">
                                                    <img alt="IMG-PRODUCT" src="images/${product.image}">
                                                    <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04"
                                                       href="images/${product.image}">
                                                        <i class="fa fa-expand"></i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-lg-5 p-b-30">
                                <div class="p-r-50 p-t-5 p-lr-0-lg">
                                    <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                                        ${product.nameProduct}
                                    </h4>
                                    <span class="mtext-106 cl2">
                							$${product.price}
                						</span>
                                    <p class="stext-102 cl3 p-t-23">
                                        ${product.description}
                                    </p>
                                    <div class="p-t-33">
                                        <div class="flex-w flex-r-m p-b-10">
                                                        <div class="size-203 flex-c-m respon6">
                                                            Size
                                                        </div>
                                                        <div class="size-204 respon6-next">
                                                            <div class="rs1-select2 bor8 bg0">
                                                                <select class="js-select2" name="time" id="sizeSelect">
                                                                    <option>${product.nameSize}</option>
                                                                </select>
                                                                <div class="dropDownSelect2"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                <div class="flex-w flex-r-m p-b-10">
                                            <div class="size-203 flex-c-m respon6">
                                                Color
                                            </div>
                                            <div class="size-204 respon6-next">
                                                <div class="rs1-select2 bor8 bg0">
                                                    <select class="js-select2" name="time" id="colorSelect">
                                                        <option>${product.nameColor}</option>
                                                    </select>
                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="flex-w flex-r-m p-b-10">
                                            <div class="size-203 flex-c-m respon6">
                                                Category
                                            </div>
                                            <div class="size-204 respon6-next">
                                                <div class="rs1-select2 bor8 bg0">
                                                        <span>${product.nameCategory}</span>

                                                    <div class="dropDownSelect2"></div>
                                                </div>
                                            </div>

                                        </div>
                                        <div class="flex-w flex-r-m p-b-10">
                                            <div class="size-204 flex-w flex-m respon6-next">
                                                <div class="wrap-num-product flex-w m-r-20 m-tb-10">
                                                    <div class="btn-num-product-down cl8 hov-btn3 trans-04 flex-c-m">
                                                        <i class="fs-16 zmdi zmdi-minus"></i>
                                                    </div>
                                                    <input class="mtext-104 cl3 txt-center num-product" name="num-product"
                                                           type="number" value="1">
                                                        <div class="btn-num-product-up cl8 hov-btn3 trans-04 flex-c-m">
                                                            <i class="fs-16 zmdi zmdi-plus"></i>
                                                        </div>
                                                </div>
                                                <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail"
                                                        onclick="addToCart()">
                                                    Add to cart
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                                        <div class="flex-m bor9 p-r-10 m-r-11">
                                            <a class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100"
                                               data-tooltip="Add to Wishlist"
                                               href="#">
                                                <i class="zmdi zmdi-favorite"></i>
                                            </a>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="bor10 m-t-50 p-t-43 p-b-40">
                            <div class="tab01">
                                <ul class="nav nav-tabs" role="tablist">
                                    <li class="nav-item p-b-10">
                                        <a class="nav-link active" data-toggle="tab" href="#description" role="tab">Description</a>
                                    </li>
                                </ul>
                                <div class="tab-content p-t-43">
                                    <div class="tab-pane fade show active" id="description" role="tabpanel">
                                        <div class="how-pos2 p-lr-15-md">
                                            <p class="stext-102 cl6">${product.description}
                                            </p>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
            `;
        } else {
            htmlAdd = `<p>Product not found</p>`;
        }
        element.innerHTML = htmlAdd;
    });
});
