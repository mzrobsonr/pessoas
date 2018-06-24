/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTBacComponent } from 'app/entities/scon-t-bac/scon-t-bac.component';
import { SconTBacService } from 'app/entities/scon-t-bac/scon-t-bac.service';
import { SconTBac } from 'app/shared/model/scon-t-bac.model';

describe('Component Tests', () => {
    describe('SconTBac Management Component', () => {
        let comp: SconTBacComponent;
        let fixture: ComponentFixture<SconTBacComponent>;
        let service: SconTBacService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTBacComponent],
                providers: []
            })
                .overrideTemplate(SconTBacComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTBacComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTBacService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTBac(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTBacs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
