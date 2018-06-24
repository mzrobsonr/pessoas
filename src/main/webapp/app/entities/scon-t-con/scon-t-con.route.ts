import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTCon } from 'app/shared/model/scon-t-con.model';
import { SconTConService } from './scon-t-con.service';
import { SconTConComponent } from './scon-t-con.component';
import { SconTConDetailComponent } from './scon-t-con-detail.component';
import { SconTConUpdateComponent } from './scon-t-con-update.component';
import { SconTConDeletePopupComponent } from './scon-t-con-delete-dialog.component';
import { ISconTCon } from 'app/shared/model/scon-t-con.model';

@Injectable({ providedIn: 'root' })
export class SconTConResolve implements Resolve<ISconTCon> {
    constructor(private service: SconTConService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTCon: HttpResponse<SconTCon>) => sconTCon.body);
        }
        return Observable.of(new SconTCon());
    }
}

export const sconTConRoute: Routes = [
    {
        path: 'scon-t-con',
        component: SconTConComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-con/:id/view',
        component: SconTConDetailComponent,
        resolve: {
            sconTCon: SconTConResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-con/new',
        component: SconTConUpdateComponent,
        resolve: {
            sconTCon: SconTConResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-con/:id/edit',
        component: SconTConUpdateComponent,
        resolve: {
            sconTCon: SconTConResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTConPopupRoute: Routes = [
    {
        path: 'scon-t-con/:id/delete',
        component: SconTConDeletePopupComponent,
        resolve: {
            sconTCon: SconTConResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTCon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
