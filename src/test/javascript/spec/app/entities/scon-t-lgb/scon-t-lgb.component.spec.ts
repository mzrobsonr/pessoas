/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTLgbComponent } from 'app/entities/scon-t-lgb/scon-t-lgb.component';
import { SconTLgbService } from 'app/entities/scon-t-lgb/scon-t-lgb.service';
import { SconTLgb } from 'app/shared/model/scon-t-lgb.model';

describe('Component Tests', () => {
    describe('SconTLgb Management Component', () => {
        let comp: SconTLgbComponent;
        let fixture: ComponentFixture<SconTLgbComponent>;
        let service: SconTLgbService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLgbComponent],
                providers: []
            })
                .overrideTemplate(SconTLgbComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTLgbComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTLgbService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTLgb(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTLgbs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
