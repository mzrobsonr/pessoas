import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTTlg } from 'app/shared/model/scon-t-tlg.model';
import { SconTTlgService } from './scon-t-tlg.service';
import { SconTTlgComponent } from './scon-t-tlg.component';
import { SconTTlgDetailComponent } from './scon-t-tlg-detail.component';
import { SconTTlgUpdateComponent } from './scon-t-tlg-update.component';
import { SconTTlgDeletePopupComponent } from './scon-t-tlg-delete-dialog.component';
import { ISconTTlg } from 'app/shared/model/scon-t-tlg.model';

@Injectable({ providedIn: 'root' })
export class SconTTlgResolve implements Resolve<ISconTTlg> {
    constructor(private service: SconTTlgService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTTlg: HttpResponse<SconTTlg>) => sconTTlg.body);
        }
        return Observable.of(new SconTTlg());
    }
}

export const sconTTlgRoute: Routes = [
    {
        path: 'scon-t-tlg',
        component: SconTTlgComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTTlg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-tlg/:id/view',
        component: SconTTlgDetailComponent,
        resolve: {
            sconTTlg: SconTTlgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTTlg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-tlg/new',
        component: SconTTlgUpdateComponent,
        resolve: {
            sconTTlg: SconTTlgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTTlg.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-tlg/:id/edit',
        component: SconTTlgUpdateComponent,
        resolve: {
            sconTTlg: SconTTlgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTTlg.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTTlgPopupRoute: Routes = [
    {
        path: 'scon-t-tlg/:id/delete',
        component: SconTTlgDeletePopupComponent,
        resolve: {
            sconTTlg: SconTTlgResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTTlg.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
