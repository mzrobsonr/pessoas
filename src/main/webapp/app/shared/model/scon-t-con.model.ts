import { ISconTLgb } from 'app/shared/model//scon-t-lgb.model';

export interface ISconTCon {
    id?: number;
    nome?: string;
    email?: string;
    idlgbc?: number;
    idlgbr?: number;
    idlgbc?: ISconTLgb;
    idlgbr?: ISconTLgb;
}

export class SconTCon implements ISconTCon {
    constructor(
        public id?: number,
        public nome?: string,
        public email?: string,
        public idlgbc?: number,
        public idlgbr?: number,
        public idlgbc?: ISconTLgb,
        public idlgbr?: ISconTLgb
    ) {}
}
