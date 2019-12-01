import {UserAccount} from "./UserAccount";

export class ProfessorAccount {
  id: number;
  account: UserAccount;
  academicRank: string;
  fieldOfResearch: string;

  static cloneAcc(acc: ProfessorAccount) {
    let clonedAcc = new ProfessorAccount();
    clonedAcc.id = acc.id;
    clonedAcc.account = UserAccount.cloneAccount(acc.account);
    clonedAcc.academicRank = acc.academicRank;
    clonedAcc.fieldOfResearch = acc.fieldOfResearch;
    return clonedAcc;
  }
}
