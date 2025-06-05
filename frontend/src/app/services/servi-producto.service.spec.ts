import { TestBed } from '@angular/core/testing';

import { ServiProductoService } from './servi-producto.service';

describe('ServiProductoService', () => {
  let service: ServiProductoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiProductoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
