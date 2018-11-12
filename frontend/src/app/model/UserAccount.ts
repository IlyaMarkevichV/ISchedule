export class UserAccount {
  accountId: number;
  email: string;
  password: string;
  role: string;

  static cloneFaculty(account: UserAccount): UserAccount {
    let prototype = new UserAccount();
    prototype.accountId = account.accountId;
    prototype.email = account.email;
    prototype.password = account.password;
    prototype.role = account.role;
    return prototype;
  }
}
