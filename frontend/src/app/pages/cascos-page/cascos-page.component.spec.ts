import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CascosPageComponent } from './cascos-page.component';

describe('CascosPageComponent', () => {
  let component: CascosPageComponent;
  let fixture: ComponentFixture<CascosPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CascosPageComponent]
    });
    fixture = TestBed.createComponent(CascosPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
