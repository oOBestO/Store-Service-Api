import os
import base64
import psycopg2

# ตั้งค่า path ของโฟลเดอร์
folder_path = "C:/Users/natta/Downloads/table_numbers_1_to_300"

# เชื่อมต่อกับ PostgreSQL
conn = psycopg2.connect(
    host="localhost",
    database="postgres",
    user="postgres",
    password="bestbest0022"
)
cursor = conn.cursor()

# อ่านไฟล์ในโฟลเดอร์
files = os.listdir(folder_path)

# เพิ่มรูปเข้าไปในฐานข้อมูล
for i, file in enumerate(files):
    file_path = os.path.join(folder_path, file)
    
    with open(file_path, 'rb') as img_file:
        base64_data = base64.b64encode(img_file.read()).decode('utf-8')
        
        # เพิ่มค่า index ด้วย
        query = """
        INSERT INTO table_image (id, index, image) 
        VALUES (%s, %s, %s)
        """
        cursor.execute(query, (i + 1, i + 1, base64_data))
        print(f"Uploaded: {file}")

# Commit และปิดการเชื่อมต่อ
conn.commit()
cursor.close()
conn.close()

print(f"{len(files)} images uploaded successfully!")
