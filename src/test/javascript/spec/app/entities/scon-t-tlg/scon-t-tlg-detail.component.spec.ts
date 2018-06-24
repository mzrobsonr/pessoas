/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTTlgDetailComponent } from 'app/entities/scon-t-tlg/scon-t-tlg-detail.component';
import { SconTTlg } from 'app/shared/model/scon-t-tlg.model';

describe('Component Tests', () => {
    describe('SconTTlg Management Detail Component', () => {
        let comp: SconTTlgDetailComponent;
        let fixture: ComponentFixture<SconTTlgDetailComponent>;
        const route = ({ data: of({ sconTTlg: new SconTTlg(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTTlgDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTTlgDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTTlgDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTTlg).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
