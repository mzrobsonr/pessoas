/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PessoasTestModule } from '../../../test.module';
import { SconTUfeComponent } from 'app/entities/scon-t-ufe/scon-t-ufe.component';
import { SconTUfeService } from 'app/entities/scon-t-ufe/scon-t-ufe.service';
import { SconTUfe } from 'app/shared/model/scon-t-ufe.model';

describe('Component Tests', () => {
    describe('SconTUfe Management Component', () => {
        let comp: SconTUfeComponent;
        let fixture: ComponentFixture<SconTUfeComponent>;
        let service: SconTUfeService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTUfeComponent],
                providers: []
            })
                .overrideTemplate(SconTUfeComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SconTUfeComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SconTUfeService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SconTUfe(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sconTUfes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
