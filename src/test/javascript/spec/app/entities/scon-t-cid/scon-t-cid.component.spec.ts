/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTCidComponent } from 'app/entities/scon-t-cid/scon-t-cid.component';
import { SconTCidService } from 'app/entities/scon-t-cid/scon-t-cid.service';
import { SconTCid } from 'app/shared/model/scon-t-cid.model';

describe('Component Tests', () => {
    describe('SconTCid Management Component', () => {
        let comp: SconTCidComponent;
        let fixture: ComponentFixture<SconTCidComponent>;
        let service: SconTCidService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTCidComponent],
                providers: []
            })
                .overrideTemplate(SconTCidComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTCidComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTCidService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTCid(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTCids[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
