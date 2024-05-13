export interface Criteria {
    
    id: number | null;
    filterId: number;
    criteriaType: number;
    operator: string;
    value: string | number;
}