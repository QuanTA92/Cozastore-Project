$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/product",
        method: "GET",
    }).done(function (data) {
        console.log("Server trả về", data);

        const productListElement = document.getElementById("productList");
        let productListHTML = "";

        // Tạo một biến đếm để theo dõi số sản phẩm trên mỗi hàng
        let productCount = 0;

        // Kiểm tra xem có dữ liệu sản phẩm hay không
        if (data && data.data && data.data.length > 0) {
            data.data.forEach(function (product) {
                if (productCount % 4 === 0) {
                    // Bắt đầu một hàng mới
                    productListHTML += `<div class="row">`;
                }

                productListHTML += `
                    <div class="col-sm-6 col-md-4 col-lg-3 p-b-35 isotope-item women">
                        <div class="block2">
                            <div class="block2-pic hov-img0">
                                <img src="images/${product.image}" alt="IMG-PRODUCT">
                                <a href="product-detail.html?productId=${product.idProduct}" class="block2-btn flex-c-m stext-103 cl2 size-102 bg0 bor2 hov-btn1 p-lr-15 trans-04 js-show-modal1">
                                    Quick View
                                </a>

                            </div>

                            <div class="block2-txt flex-w flex-t p-t-14">

                                <div class="block2-txt-child1 flex-col-l">
                                    <a href="product-detail.html?productId=${product.idProduct}" class="stext-104 cl4 hov-cl1 trans-04 js-name-b2 p-b-6">
                                        ${product.nameProduct}
                                    </a>

                                    <span class="stext-105 cl3">
                                        $${product.price}
                                    </span>

                                    <div class="btn-group" role="group">
                                        <a href="product-edit.html?idProduct=${product.idProduct}" class="btn btn-primary">EDIT</a>
                                        <button class="btn btn-danger btn-delete-product" data-product-id="${product.idProduct}">DELETE</button>
                                    </div>

                                </div>


                            </div>

                        </div>
                    </div>
                `;

                productCount++;

                if (productCount % 4 === 0) {
                    // Kết thúc hàng hiện tại
                    productListHTML += `</div>`;
                }
            });

            // Kiểm tra xem còn sản phẩm nào chưa đóng hàng
            if (productCount % 4 !== 0) {
                // Kết thúc hàng cuối (nếu không chia hết cho 4)
                productListHTML += `</div>`;
            }
        } else {
            // Xử lý trường hợp không có dữ liệu
            productListHTML = "Không có sản phẩm.";
        }

        productListElement.innerHTML = productListHTML;
    });

    // Xử lý sự kiện bấm nút xóa
    $(document).on("click", ".btn-delete-product", function() {
        const productId = $(this).data("product-id");
        if (confirm("Bạn có chắc muốn xóa sản phẩm này?")) {
            // Gửi yêu cầu DELETE đến API @DeleteMapping("/{idProduct}")
            $.ajax({
                url: `http://localhost:8080/product/${productId}`,
                method: "DELETE",
            }).done(function () {
                // Xử lý sau khi xóa sản phẩm (ví dụ: cập nhật giao diện)
                alert("Sản phẩm đã được xóa.");

                location.reload();
            });
        }
    });
});