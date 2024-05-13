import { Criteria } from "./criteria";

export interface Filter {
    
    id: number | null;
    name: string;
    selection: number;
    criterias: Criteria[];
    createdDate: string;
    modifiedDate: string | null;

}