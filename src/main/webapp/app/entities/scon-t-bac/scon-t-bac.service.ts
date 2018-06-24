import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTBac } from 'app/shared/model/scon-t-bac.model';

type EntityResponseType = HttpResponse<ISconTBac>;
type EntityArrayResponseType = HttpResponse<ISconTBac[]>;

@Injectable({ providedIn: 'root' })
export class SconTBacService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-bacs';

    constructor(private http: HttpClient) {}

    create(sconTBac: ISconTBac): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTBac);
        return this.http
            .post<ISconTBac>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTBac: ISconTBac): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTBac);
        return this.http
            .put<ISconTBac>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTBac>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTBac[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTBac: ISconTBac): ISconTBac {
        const copy: ISconTBac = Object.assign({}, sconTBac, {
            timestamp: sconTBac.timestamp != null && sconTBac.timestamp.isValid() ? sconTBac.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTBac: ISconTBac) => {
            sconTBac.timestamp = sconTBac.timestamp != null ? moment(sconTBac.timestamp) : null;
        });
        return res;
    }
}
