package org.taonaw.identityaccess.usecase

class MemberAccountNotFound() : Exception("会員アカウントが見つかりません。")
class UserIdAlreadyRegistered() : Exception("ユーザーIDは既に登録されています。")
class MemberUnAuthentication() : Exception("パスワードが間違っています。")

class StaffAccountNotFound() : Exception("スタッフアカウントが見つかりません。")
class EmailAlreadyRegistered() : Exception("メールアドレスは既に登録されています。")
class StaffUnAuthentication() : Exception("パスワードが間違っています。")