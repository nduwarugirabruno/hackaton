import {Country} from "../../enum/country.enum";

export interface Address {
    id: string;
    country: Country;
    city: string;
    firstLine: string;
    secondLine: string;
}
