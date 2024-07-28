import { Country } from "../../enum/country.enum";

export interface Telephone {
    id: string;
    country: Country;
    phoneNumber: number;
}
