import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTLgb } from 'app/shared/model/scon-t-lgb.model';

type EntityResponseType = HttpResponse<ISconTLgb>;
type EntityArrayResponseType = HttpResponse<ISconTLgb[]>;

@Injectable({ providedIn: 'root' })
export class SconTLgbService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-lgbs';

    constructor(private http: HttpClient) {}

    create(sconTLgb: ISconTLgb): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTLgb);
        return this.http
            .post<ISconTLgb>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTLgb: ISconTLgb): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTLgb);
        return this.http
            .put<ISconTLgb>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTLgb>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTLgb[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTLgb: ISconTLgb): ISconTLgb {
        const copy: ISconTLgb = Object.assign({}, sconTLgb, {
            timestamp: sconTLgb.timestamp != null && sconTLgb.timestamp.isValid() ? sconTLgb.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTLgb: ISconTLgb) => {
            sconTLgb.timestamp = sconTLgb.timestamp != null ? moment(sconTLgb.timestamp) : null;
        });
        return res;
    }
}
