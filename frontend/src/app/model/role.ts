export class Role {
  id: number;
  roleName: string;

  static cloneRole(role: Role): Role {
    let clonedRole = new Role();
    clonedRole.id = role.id;
    clonedRole.roleName = role.roleName;
    return clonedRole;
  }
}
