# Hướng dẫn Setup Sepay Sandbox - Đơn giản nhất

## Tóm tắt nhanh

Sandbox Sepay **KHÔNG kết nối với ngân hàng thật**. Bạn chỉ cần:
1. Tạo webhook trong Sepay (không cần bank account)
2. Test bằng script PowerShell/Bash

## Bước 1: Đăng ký & kích hoạt Sepay Sandbox

1. Truy cập: https://my.dev.sepay.vn
2. Đăng ký tài khoản
3. **Liên hệ Sepay để kích hoạt**:
   - Email: support@sepay.vn
   - Hoặc tìm số điện thoại tại: https://developer.sepay.vn

## Bước 2: Tạo Bank Account Test (bắt buộc)

Sandbox vẫn yêu cầu phải có bank account trong hệ thống (nhưng KHÔNG kết nối thật).

1. Vào menu: **Tài khoản ngân hàng** hoặc **Bank Accounts**
2. Click **"+ Thêm tài khoản"** hoặc **"Add Account"**
3. Điền thông tin test (giá trị bất kỳ):
   ```
   Tên ngân hàng: Vietcombank
   Số tài khoản: 0123456789
   Tên chủ tài khoản: HUNG HYPEBEAST STORE
   ```
4. Click **Lưu**

**Lưu ý**: 
- Đây chỉ là thông tin test, KHÔNG kết nối với ngân hàng thật
- Chỉ cần để có option trong dropdown khi tạo webhook

## Bước 3: Tạo Webhook (theo UI bạn đang thấy)

Trong trang "Thêm Webhook", điền như sau:

### 📝 Đặt tên
```
Tên: Hung Hypebeast Webhook
```

### 1️⃣ Chọn sự kiện
```
Bắn Webhooks khi: Có tiền vào
```

### 2️⃣ Chọn điều kiện
```
Khi tới tài khoản ngân hàng là: Chọn bank account bạn vừa tạo ở Bước 2
                                  (Vietcombank - 0123456789)

Bỏ qua nếu nội dung giao dịch không có Code thanh toán?: KHÔNG CHECK
```

### 3️⃣ Thuộc tính Webhooks
```
Gọi tới URL: https://your-ngrok-url.ngrok.io/api/sepay/webhook
              (hoặc http://localhost:8080/api/sepay/webhook nếu test local)

Là Webhooks xác thực thanh toán?: KHÔNG (hoặc Check - không quan trọng)

Gọi lại Webhooks khi?: ✅ CHECK "HTTP status Code không nằm trong phạm vi từ 200 đến 299"
```

### 4️⃣ Cấu hình chứng thực Webhooks
```
Kiểu chứng thực: Không cần chứng thực

Request Content type: application/json
```

**LƯU LẠI API KEY**: Khi tạo xong, Sepay sẽ generate API key → Lưu lại ngay!

## Bước 4: Setup Environment Variables

Tạo file `.env` hoặc set environment variables:

```bash
# API key từ Bước 2
SEPAY_API_KEY=your-generated-api-key

# Bank info - dùng giá trị test bất kỳ
SEPAY_BANK_ACCOUNT=0123456789
SEPAY_BANK_ACCOUNT_NAME=HUNG HYPEBEAST STORE
SEPAY_BANK_NAME=Vietcombank
```

## Bước 5: Test Webhook bằng Script

### Option A: Test local (không cần ngrok)

1. Chạy Spring Boot app:
   ```bash
   # Set environment variables trước
   mvnw spring-boot:run
   ```

2. Chạy script test:
   ```powershell
   # Windows PowerShell
   .\docs\test-sepay-webhook.ps1 -OrderNumber "ORD-1234567890" -Amount 500000 -ApiKey "your-api-key"
   ```

3. Kiểm tra logs:
   ```
   INFO: Received Sepay webhook: transactionId=xxx
   INFO: Successfully processed Sepay webhook for order: ORD-1234567890
   ```

### Option B: Test với ngrok (nếu muốn test giống production)

1. Cài ngrok: https://ngrok.com/download

2. Chạy ngrok:
   ```bash
   ngrok http 8080
   ```

3. Copy HTTPS URL (ví dụ: `https://abc123.ngrok.io`)

4. Update webhook URL trong Sepay dashboard:
   ```
   https://abc123.ngrok.io/api/sepay/webhook
   ```

5. Test bằng script hoặc Sepay dashboard

## Workflow test hoàn chỉnh

```bash
# 1. Tạo order qua API
POST /api/checkout/order
{
  "paymentMethod": "SEPAY",
  "customerName": "Test User",
  ...
}

# Response trả về orderNumber và amount
# Ví dụ: ORD-1737382800000, amount: 500000

# 2. Test webhook
.\docs\test-sepay-webhook.ps1 `
  -OrderNumber "ORD-1737382800000" `
  -Amount 500000 `
  -ApiKey "your-api-key"

# 3. Verify
# - Check logs: order status = "paid"
# - Check database: payment_transactions.status = "success"
# - Check email: customer nhận email confirmation
```

## Troubleshooting

### Q: Không tìm thấy bank account để chọn?
**A**: Bạn cần tạo bank account test trước (Bước 2). Đây chỉ là record test, không kết nối ngân hàng thật.

### Q: Webhook không nhận được?
**A**: Check:
- Application đang chạy
- Webhook URL đúng
- API key đúng
- Nếu dùng localhost, phải dùng ngrok

### Q: Làm sao biết webhook đã gửi thành công?
**A**: Check logs Spring Boot:
```
INFO: Received Sepay webhook: transactionId=12345
INFO: Successfully processed Sepay webhook for order: ORD-xxx
```

## Files script test

- **Windows**: `docs/test-sepay-webhook.ps1`
- **Linux/Mac**: `docs/test-sepay-webhook.sh`

## Tổng kết

✅ Sandbox = test 100% mô phỏng
✅ Không cần ngân hàng thật
✅ Không cần bank account trong Sepay
✅ Test bằng script thay vì chuyển khoản thật
✅ Nhanh, đơn giản, hiệu quả

Nếu gặp khó khăn, liên hệ: support@sepay.vn
