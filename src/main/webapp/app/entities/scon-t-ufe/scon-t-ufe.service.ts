import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTUfe } from 'app/shared/model/scon-t-ufe.model';

type EntityResponseType = HttpResponse<ISconTUfe>;
type EntityArrayResponseType = HttpResponse<ISconTUfe[]>;

@Injectable({ providedIn: 'root' })
export class SconTUfeService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-ufes';

    constructor(private http: HttpClient) {}

    create(sconTUfe: ISconTUfe): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTUfe);
        return this.http
            .post<ISconTUfe>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTUfe: ISconTUfe): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTUfe);
        return this.http
            .put<ISconTUfe>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTUfe>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTUfe[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTUfe: ISconTUfe): ISconTUfe {
        const copy: ISconTUfe = Object.assign({}, sconTUfe, {
            timestamp: sconTUfe.timestamp != null && sconTUfe.timestamp.isValid() ? sconTUfe.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTUfe: ISconTUfe) => {
            sconTUfe.timestamp = sconTUfe.timestamp != null ? moment(sconTUfe.timestamp) : null;
        });
        return res;
    }
}
