/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTLogDetailComponent } from 'app/entities/scon-t-log/scon-t-log-detail.component';
import { SconTLog } from 'app/shared/model/scon-t-log.model';

describe('Component Tests', () => {
    describe('SconTLog Management Detail Component', () => {
        let comp: SconTLogDetailComponent;
        let fixture: ComponentFixture<SconTLogDetailComponent>;
        const route = ({ data: of({ sconTLog: new SconTLog(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTLogDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTLogDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTLogDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTLog).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
