import { Role } from "../../enum/role.enum";
import { Address } from "../app/address.class";
import { Telephone } from "../app/telephone.class";

export interface Admin {
    id: string;
    firstName: string;
    lastName: string;
    address: Address;
    phone: Telephone;
    email: string;
    password: string;
    role: Role;
}
