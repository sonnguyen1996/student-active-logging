# Student Active Logging
This module is a connector for mobile-client and image processing modules.
## Features
- Upload student face and organization in storage.
- Tracking student's activities event: key and mouse, chatting.
# API Document
```bat
domain:localhost:8081
```
### 1. uploadFaceLog
Upload student image from mobile client.

**URL** : `/logging/uploadFaceLog/`

**Method** : `POST`

**Content-Type** : `multipart/form-data`

**Auth required** : NO

**Permissions required** : None

## Success Response

**Code** : `200 OK`

**request examples**

For a User with msisdn: 123456789

```json
{
    "file": "face_capture.jpg",
    "userData": "{"msisdn":"123456789","room_id":"12321","media_type" : "JPG"}"
}
```

**response examples**

```json
{
    "code": 200,
    "code": "Thành công",
    "data": null
}
```