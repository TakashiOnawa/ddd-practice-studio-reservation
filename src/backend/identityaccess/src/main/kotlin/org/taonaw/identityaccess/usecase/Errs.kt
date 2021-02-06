package org.taonaw.identityaccess.usecase

class UserIdAlreadyRegistered() : Exception("ユーザーIDは既に登録されています。")
class EmailAlreadyRegistered() : Exception("メールアドレスは既に登録されています。")