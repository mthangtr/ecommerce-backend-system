EMAIL YÊU CẦU DỰ ÁN
From: Anh Hùng - Founder "Hung Hypebeast" (Local Brand thời trang)
To: Team Dev
Subject: Cần làm gấp Backend hệ thống e-commerce mới (Phase 1)
Chào team,
Như đã trao đổi cafe hôm qua, hiện tại shop anh đang vỡ trận vì quản lý thủ công quá. Anh cần
tụi em build gấp cho anh cái hệ thống backend (chưa cần giao diện đẹp đâu, anh có team
Front-end riêng rồi, tụi nó cần API để gọi thôi). Mục tiêu xong trong 2 tuần để kịp đợt sale cuối
tháng.
Dưới đây là mấy cái "đau đầu" nhất anh cần tụi em giải quyết:
1. Vấn đề hàng hóa & Hiển thị (Catalog):
Quản lý: Bên anh bán quần áo nên một mẫu (ví dụ: Áo Thun Rồng) sẽ có nhiều biến thể
(Size M, L, XL / Màu Đen, Trắng). Quản lý được từng cái SKU này.
Hiển thị: Khách vào web phải thấy được danh sách sản phẩm tải nhanh, phân trang rõ
ràng (chứ load 1 cục 1000 cái là chết). Cần lọc được theo khoảng giá hoặc loại áo (áo
thun, hoodie...) để khách dễ tìm.
2. Giỏ hàng (Shopping Cart):
Anh cần tính năng giỏ hàng để khách gom nhiều món mua một lần cho tiện ship.
Cho phép khách thêm hàng vào giỏ, tăng giảm số lượng, hoặc xóa bớt món không
thích.
Lưu ý: Giỏ hàng cũng phải check sơ sơ cái tồn kho, đừng để khách thêm 10 cái vào giỏ
trong khi kho còn có 2 cái.
3. Vấn đề "Hết hàng mà vẫn bán" (Inventory - Critical):
Đây là cái quan trọng nhất. Đợt trước sale, cái áo còn đúng 1 cái size L. Hai ông khách
cùng bấm mua một lúc. Hệ thống cũ vẫn báo thành công cho cả 2, cuối cùng anh phải
gọi điện xin lỗi 1 ông, bị chửi te tua.
Anh muốn là khi khách hàng bắt đầu vào trang thanh toán (từ giỏ hàng checkout
sang), là phải giữ cái đống hàng đó cho họ trong 10-15 phút. Người khác không mua
được nữa. Hết giờ không trả tiền thì nhả ra.
Đặc biệt là cái "cái cuối cùng" (last item) trong kho, phải xử lý cho chuẩn.
4. Thanh toán & Đơn hàng (Checkout):
Quy trình tạo đơn: Khách điền thông tin ship -> Chọn thanh toán -> Bấm "Đặt hàng".
Lúc này hệ thống mới chốt đơn.
Anh cho khách chọn 2 kiểu thanh toán:

COD (Giao hàng thu tiền): Tạo đơn xong là xong.
Chuyển khoản (SePay): Anh đang dùng SePay. Khách chuyển khoản xong, hệ
thống tự động biết đơn nào đã trả tiền để đổi trạng thái luôn. Tuy nhiên nếu không
kịp thì để phase sau.
5. Theo dõi đơn hàng (Tracking):
Khách mua xong thì gửi cho họ cái email xác nhận ("Cảm ơn đã mua hàng..."), trong đó
kèm cái link để họ bấm vào xem đơn hàng đang ở trạng thái nào (Đã xác nhận, Đã
thanh toán, Đang giao...).
Quan trọng: Đừng bắt họ đăng nhập hay tạo tài khoản gì cả, phiền phức lắm. Cứ bấm
link là xem được thôi cho tiện.
6. Admin:
Chưa cần làm phần nhập liệu sản phẩm (Catalog): Phần này anh nhờ team data nhập
thẳng vào Database cũng được, chưa cần API tạo/sửa sản phẩm gấp, để phase sau
hoàn thiện cũng được.
Cái anh cần ngay:
API để xem danh sách đơn hàng đổ về.
Quan trọng: API để nhân viên kho đổi trạng thái đơn hàng (Ví dụ: Từ "Đã thanh
toán" -> "Đang giao hàng", hoặc "Hủy đơn" nếu khách gọi điện hủy).

Tóm lại: 2 tuần, Backend API ngon, có Giỏ hàng, catalog, đặc biệt không lỗi tồn kho, có gửi mail
tracking đơn giản. Admin chỉ cần xem và xử lý đơn.
Báo giá và phương án cho anh sớm nhé.