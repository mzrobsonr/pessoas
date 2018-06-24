import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISconTPai } from 'app/shared/model/scon-t-pai.model';

type EntityResponseType = HttpResponse<ISconTPai>;
type EntityArrayResponseType = HttpResponse<ISconTPai[]>;

@Injectable({ providedIn: 'root' })
export class SconTPaiService {
    private resourceUrl = SERVER_API_URL + 'api/scon-t-pais';

    constructor(private http: HttpClient) {}

    create(sconTPai: ISconTPai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTPai);
        return this.http
            .post<ISconTPai>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    update(sconTPai: ISconTPai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(sconTPai);
        return this.http
            .put<ISconTPai>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISconTPai>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertDateFromServer(res));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISconTPai[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(sconTPai: ISconTPai): ISconTPai {
        const copy: ISconTPai = Object.assign({}, sconTPai, {
            timestamp: sconTPai.timestamp != null && sconTPai.timestamp.isValid() ? sconTPai.timestamp.format(DATE_FORMAT) : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.timestamp = res.body.timestamp != null ? moment(res.body.timestamp) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((sconTPai: ISconTPai) => {
            sconTPai.timestamp = sconTPai.timestamp != null ? moment(sconTPai.timestamp) : null;
        });
        return res;
    }
}
