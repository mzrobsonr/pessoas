/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTConComponent } from 'app/entities/scon-t-con/scon-t-con.component';
import { SconTConService } from 'app/entities/scon-t-con/scon-t-con.service';
import { SconTCon } from 'app/shared/model/scon-t-con.model';

describe('Component Tests', () => {
    describe('SconTCon Management Component', () => {
        let comp: SconTConComponent;
        let fixture: ComponentFixture<SconTConComponent>;
        let service: SconTConService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTConComponent],
                providers: []
            })
                .overrideTemplate(SconTConComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTConComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTConService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTCon(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTCons[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
