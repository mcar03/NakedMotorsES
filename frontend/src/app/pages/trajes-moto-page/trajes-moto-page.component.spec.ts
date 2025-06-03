import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TrajesMotoPageComponent } from './trajes-moto-page.component';

describe('TrajesMotoPageComponent', () => {
  let component: TrajesMotoPageComponent;
  let fixture: ComponentFixture<TrajesMotoPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TrajesMotoPageComponent]
    });
    fixture = TestBed.createComponent(TrajesMotoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
