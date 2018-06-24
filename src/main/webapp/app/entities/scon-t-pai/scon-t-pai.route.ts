import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { SconTPai } from 'app/shared/model/scon-t-pai.model';
import { SconTPaiService } from './scon-t-pai.service';
import { SconTPaiComponent } from './scon-t-pai.component';
import { SconTPaiDetailComponent } from './scon-t-pai-detail.component';
import { SconTPaiUpdateComponent } from './scon-t-pai-update.component';
import { SconTPaiDeletePopupComponent } from './scon-t-pai-delete-dialog.component';
import { ISconTPai } from 'app/shared/model/scon-t-pai.model';

@Injectable({ providedIn: 'root' })
export class SconTPaiResolve implements Resolve<ISconTPai> {
    constructor(private service: SconTPaiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((sconTPai: HttpResponse<SconTPai>) => sconTPai.body);
        }
        return Observable.of(new SconTPai());
    }
}

export const sconTPaiRoute: Routes = [
    {
        path: 'scon-t-pai',
        component: SconTPaiComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTPai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-pai/:id/view',
        component: SconTPaiDetailComponent,
        resolve: {
            sconTPai: SconTPaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTPai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-pai/new',
        component: SconTPaiUpdateComponent,
        resolve: {
            sconTPai: SconTPaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTPai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'scon-t-pai/:id/edit',
        component: SconTPaiUpdateComponent,
        resolve: {
            sconTPai: SconTPaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTPai.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const sconTPaiPopupRoute: Routes = [
    {
        path: 'scon-t-pai/:id/delete',
        component: SconTPaiDeletePopupComponent,
        resolve: {
            sconTPai: SconTPaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'pessoasApp.sconTPai.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
