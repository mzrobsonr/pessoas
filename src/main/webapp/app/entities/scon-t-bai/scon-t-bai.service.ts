import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTBai } from 'app/shared/model/scon-t-bai.model';

type EntityResponseType = HttpResponse<ISconTBai>;
type EntityArrayResponseType = HttpResponse<ISconTBai[]>;

@Injectable({ providedIn: 'root' })
export class SconTBaiService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-bais';

    constructor(private http: HttpClient) {}

    create(sconTBai: ISconTBai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTBai);
        return this.http
            .post<ISconTBai>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTBai: ISconTBai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTBai);
        return this.http
            .put<ISconTBai>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTBai>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTBai[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTBai: ISconTBai): ISconTBai {
        const copy: ISconTBai = Object.assign({}, sconTBai, {
            timestamp: sconTBai.timestamp != null && sconTBai.timestamp.isValid() ? sconTBai.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTBai: ISconTBai) => {
            sconTBai.timestamp = sconTBai.timestamp != null ? moment(sconTBai.timestamp) : null;
        });
        return res;
    }
}
