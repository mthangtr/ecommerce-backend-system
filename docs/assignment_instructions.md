ASSIGNMENT INSTRUCTION: E-COMMERCE BACKEND SYSTEM
(PHASE 1)
I. TỔNG QUAN
Học viên sẽ đóng vai trò là Backend Developer tiếp nhận dự án từ khách hàng (Anh Hùng -
Founder Local Brand). Dựa trên yêu cầu đầu vào, học viên cần phân tích, thiết kế và hiện thực
hóa hệ thống Backend (Headless) trong thời gian 2 tuần.
II. SẢN PHẨM BÀN GIAO (DELIVERABLES)
Học viên cần nộp 02 thành phần chính:
1. Báo cáo kỹ thuật (Technical Report)
Định dạng: PDF hoặc Markdown, bao gồm:
(1) Đánh giá sơ bộ & Phân tích yêu cầu:
Xác định Scope: Dựa trên email khách hàng, liệt kê các tính năng Phải làm (Must-have)
và Nên làm (Nice-to-have) trong 2 tuần.
Gap Analysis: So sánh giữa yêu cầu thô (Email) và yêu cầu kỹ thuật thực tế.
Đánh giá khả năng hoàn thiện: Cam kết bao nhiêu % so với yêu cầu? Nếu không kịp, đề
xuất cắt giảm tính năng nào? (Ví dụ: Thay vì tích hợp SePay thật, có thể giả lập
Webhook).
(2) Thiết kế hệ thống (System Design):
DB Design (3): Sơ đồ ERD (Entity Relationship Diagram) chi tiết các bảng, kiểu dữ liệu
và quan hệ. Giải thích lý do thiết kế (Tại sao tách bảng X? Tại sao cần bảng Y?).
LLD (Low-Level Design) (4):
Danh sách API Endpoints (chỉ cần Method, URL, Description).
Sequence Diagram (Biểu đồ tuần tự) cho các luồng được đánh giá là quan trọng
hoặc phức tạp nhất.
2. Source Code & Hướng dẫn
Source Code: Link GitHub Repository (Cấu trúc thư mục rõ ràng, tuân thủ Clean
Architecture/Modular).
Setup Guide: File README.md hướng dẫn chi tiết:
Cách cài đặt môi trường (Node version, DB setup).
Cách chạy Migration/Seed dữ liệu mẫu.
Cách chạy server.
API Collection: File export từ Postman/Insomnia hoặc Link Swagger để giảng viên test.

III. CÁC HOẠT ĐỘNG
Học viên cần thực hiện theo lộ trình sau, cần chủ động guide & xin review xuyên suốt quá trình
để tránh đi sai hướng (lưu ý, hoạt động này cũng có trọng số điểm đáng kể):
1. Giai đoạn Đánh giá sơ bộ & Phân tích (Ngày 1-2)
Đọc kỹ Email khách hàng và soạn thảo đặc tả yêu cầu nghiệp vụ.
Nhiệm vụ: Tự đặt câu hỏi "Tại sao?". Ví dụ: Tại sao phải giữ hàng 10 phút? Nếu server sập
lúc đang giữ hàng thì sao?
Đánh giá khả năng hoàn thiện
Đánh giá sơ bộ scope so với năng lực phát triển: cần nêu rõ cơ sở/nguyên do cụ thể (Ví
dụ: Logic tồn kho được yêu cầu tương đối phức tạp, nêu rõ cụ thể tại sao lại phức tạp,
nên chưa tự tin để hoàn thiện trong 2 ngày để kịp thời hạn tổng 2 tuần)
Đề xuất phương án điều chỉnh scope khớp với năng lực phát triển (Ví dụ: Đề xuất giảm
độ phức tạp logic tồn kho một cách cụ thể).
Lưu ý: Trong thực tế, việc điều chỉnh này là 1 quá trình nego rất “căng thẳng" với khách
hàng khi presale, nhưng trong training, học viên chủ động nêu rõ và trao đổi với giảng
viên để linh hoạt điều chỉnh, từ đó tạo cơ sở để soạn thảo đặc tả yêu cầu nghiệp vụ với
scope mới.
Tips: việc đánh giá khả năng hoàn thiện dựa trên cơ sở là “kinh nghiệm của người đi
trước", có thể thu thập từ web, AI tool hoặc chuyên gia thực nếu có network.
2. Giai đoạn Thiết kế (Ngày 3-4)
Vẽ ERD. Chốt các trường dữ liệu và các ràng buộc.
Thiết kế API Contract: Đầu vào nhận gì, đầu ra trả gì, mã lỗi là gì?
Quan trọng: Lên phương án kỹ thuật cho các luồng quan trọng, như bài toán "Last Item"
(Sử dụng Database Lock, Atomic Update hay Redis?).
3. Giai đoạn Code & Test (Ngày 5-14)
Dựng khung dự án (Skeleton).
Implement các tính năng
Self-test bằng Postman.
Viết README.