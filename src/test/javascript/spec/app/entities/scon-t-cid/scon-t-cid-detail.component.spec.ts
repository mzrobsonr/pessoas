/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PessoasTestModule } from '../../../test.module';
import { SconTCidDetailComponent } from 'app/entities/scon-t-cid/scon-t-cid-detail.component';
import { SconTCid } from 'app/shared/model/scon-t-cid.model';

describe('Component Tests', () => {
    describe('SconTCid Management Detail Component', () => {
        let comp: SconTCidDetailComponent;
        let fixture: ComponentFixture<SconTCidDetailComponent>;
        const route = ({ data: of({ sconTCid: new SconTCid(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PessoasTestModule],
                declarations: [SconTCidDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SconTCidDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SconTCidDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sconTCid).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
