export class Role {
  roleId: number;
  roleName: string;

  static cloneRole(role: Role): Role {
    let clonedRole = new Role();
    clonedRole.roleId = role.roleId;
    clonedRole.roleName = role.roleName;
    return clonedRole;
  }
}
